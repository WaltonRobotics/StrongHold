package org.usfirst.frc.team2974.robot.sensors;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

/**
 * Library ported from Adafruit Ardunio library. See below for original license
 * info. IMU Library not included, routines return arrays of double. Code does
 * not use the sensor API from Adafruit -- call getVector/getQuaternion to get
 * data. Otherwise use as directed.
 * <p>
 * Java version (c) Tim Stanistreet 2016. MIT license
 * <p>
 * ***************************************************************************
 * This is a library for the BNO055 orientation sensor
 * <p>
 * Designed specifically to work with the Adafruit BNO055 Breakout. Pick one up
 * today in the adafruit shop! ------> http://www.adafruit.com/products
 * <p>
 * These sensors use I2C to communicate, 2 pins are required to interface.
 * <p>
 * Adafruit invests time and resources providing this open source code, please
 * support Adafruit andopen-source hardware by purchasing products from
 * Adafruit!
 * <p>
 * Written by KTOWN for Adafruit Industries. MIT license, all text above must be
 * included in any redistribution
 */
public class BNO055 {

	/**
	 * Exception thrown when things go wrong with reading or writing the BNO055
	 */
	public static class BNO055Exception extends Exception {

		private static final long serialVersionUID = 1L;

		public BNO055Exception() {
			super();
		}

		public BNO055Exception(final String message) {
			super(message);
		}

		public BNO055Exception(final String message, final Throwable cause) {
			super(message, cause);
		}

		public BNO055Exception(final String message, final Throwable cause, final boolean enableSuppression,
				final boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public BNO055Exception(final Throwable cause) {
			super(cause);
		}
	}

	/**
	 * Object defining calibration status response
	 */
	public final class CalibrationStatus {

		public final int system;

		public final int gyro;
		public final int accel;
		public final int mag;

		private CalibrationStatus(final int system, final int gyro, final int accel, final int mag) {
			this.system = system;
			this.gyro = gyro;
			this.accel = accel;
			this.mag = mag;
		}
	}

	/**
	 * Object defining calibration offsets
	 */
	public final class Offsets {

		public final int[] accelOffset;

		public final int[] gyroOffset;
		public final int[] magOffset;
		public final int accelRadius;

		public final int magRadius;

		public Offsets(final int[] accelOffset, final int[] gyroOffset, final int[] magOffset, final int accelRadius,
				final int magRadius) {
			this.accelOffset = accelOffset;

			this.gyroOffset = gyroOffset;

			this.magOffset = magOffset;

			this.accelRadius = accelRadius;
			this.magRadius = magRadius;
		}
	}

	/**
	 * Enumeration defining the various operation modes for the BNO055
	 */
	public enum OperationMode {
		OPERATION_MODE_CONFIG(0X00), OPERATION_MODE_ACCONLY(0X01), OPERATION_MODE_MAGONLY(0X02), OPERATION_MODE_GYRONLY(
				0X03), OPERATION_MODE_ACCMAG(0X04), OPERATION_MODE_ACCGYRO(
						0X05), OPERATION_MODE_MAGGYRO(0X06), OPERATION_MODE_AMG(0X07), OPERATION_MODE_IMUPLUS(
								0X08), OPERATION_MODE_COMPASS(0X09), OPERATION_MODE_M4G(
										0X0A), OPERATION_MODE_NDOF_FMC_OFF(0X0B), OPERATION_MODE_NDOF(0X0C);

		/**
		 * The number which represents the operation mode on the BNO055
		 */
		private final int value;

		OperationMode(final int value) {
			this.value = value;
		}
	}

	/**
	 * Enumeration defining the various power modes for the BNO055
	 */
	public enum PowerMode {
		POWER_MODE_NORMAL(0X00), POWER_MODE_LOWPOWER(0X01), POWER_MODE_SUSPEND(0X02);

		/**
		 * The number which represents the power mode on the BNO055
		 */
		private final int value;

		PowerMode(final int value) {
			this.value = value;
		}
	}

	/**
	 * Enumeration for BNO055 register names
	 */
	private enum Register {
		/* Page id register definition */
		PAGE_ID(0X07),

		/* PAGE0 REGISTER DEFINITION START */
		CHIP_ID(0x00), ACCEL_REV_ID(0x01), MAG_REV_ID(0x02), GYRO_REV_ID(0x03), SW_REV_ID(0x04, 2), BL_REV_ID(0X06),

		/* Accel data register */
		ACCEL_DATA(0X08, 2, 3, true),

		/* Mag data register */
		MAG_DATA(0X0E, 2, 3, true),

		/* Gyro data registers */
		GYRO_DATA(0X14, 2, 3, true),

		/* Euler data registers */
		EULER(0X1A, 2, 3, true),

		/* Quaternion data registers */
		QUATERNION_DATA(0X20, 2, 4, true),

		/* Linear acceleration data registers */
		LINEAR_ACCEL_DATA(0X28, 2, 3, true),

		/* Gravity data registers */
		GRAVITY_DATA(0X2E, 2, 3, true),

		/* Temperature data register */
		TEMP(0X34),

		/* Status registers */
		CALIB_STAT(0X35), SELFTEST_RESULT(0X36), INTR_STAT(0X37),

		SYS_CLK_STAT(0X38), SYS_STAT(0X39), SYS_ERR(0X3A),

		/* Unit selection register */
		UNIT_SEL(0X3B), DATA_SELECT(0X3C),

		/* Mode registers */
		OPR_MODE(0X3D), PWR_MODE(0X3E),

		SYS_TRIGGER(0X3F), TEMP_SOURCE(0X40),

		/* Axis remap registers */
		AXIS_MAP_CONFIG(0X41), AXIS_MAP_SIGN(0X42),

		/* SIC registers */
		SIC_MATRIX(0X43, 2, 8, true),

		/* Accelerometer Offset registers */
		ACCEL_OFFSET(0X55, 2, 3, true),

		/* Magnetometer Offset registers */
		MAG_OFFSET(0X5B, 2, 3, true),

		/* Gyroscope Offset register s */
		GYRO_OFFSET(0X61, 2, 3, true),

		/* Radius registers */
		ACCEL_RADIUS(0X67, 2, true), MAG_RADIUS(0X69, 2, true);

		/**
		 * The address of the register in the BNO055
		 */
		private final int address;

		/**
		 * The number of bytes stored at the register
		 */
		private final int bytes;

		/**
		 * The number of vector components stored at the register
		 */
		private final int components;

		/**
		 * The number is signed
		 */
		private final boolean signed;

		/*
		 * Create a scalar register which represents a byte value
		 *
		 * @param address The address of the register
		 */
		Register(final int address) {
			this(address, 1, 1, false);
		}

		/**
		 * Create a scalar register which represents an arbitrary number of
		 * bytes (assumed little-endian)
		 *
		 * @param address
		 *            The address of the register
		 * @param bytes
		 *            The number of bytes which store the value at the register
		 */
		Register(final int address, final int bytes) {
			this(address, bytes, 1, false);
		}

		/**
		 * Create a scalar register which represents and arbitrary number of
		 * bytes (assumed little-endian) which can be signed
		 *
		 * @param address
		 *            The address of the register
		 * @param bytes
		 *            The number of bytes which store the value at the register
		 * @param signed
		 *            The value stored at the register is a signed value
		 */
		Register(final int address, final int bytes, final boolean signed) {
			this(address, bytes, 1, signed);
		}

		/**
		 * Create a vector register with an arbitrary number of bytes (assumed
		 * little-endian)
		 *
		 * @param address
		 *            The address of the register
		 * @param bytes
		 *            The number of bytes which store the value at the register
		 * @param components
		 *            THe number of components in the vector
		 * @param signed
		 *            The value stored at the register is a signed value
		 */
		Register(final int address, final int bytes, final int components, final boolean signed) {
			if (bytes < 1 || bytes > 4 || bytes == 3)
				throw new IllegalArgumentException("Number of bytes should be 1, 2 or 4");

			this.address = address;
			this.bytes = bytes;
			this.components = components;
			this.signed = signed;
		}

		/**
		 * Process the buffer into the correct int value
		 *
		 * @param buf
		 *            The buffer contain the value to read
		 * @param index
		 *            The location in the buffer containing the LSB
		 * @return Integer value containing the processed value
		 */
		private int processBuf(final byte[] buf, final int index) {
			if (buf.length < index + bytes)
				throw new IndexOutOfBoundsException("Index is out of bounds when reading from register " + toString());

			int value = 0;

			// Process LSBs
			for (int j = 0; j < bytes - 1; j++)
				value |= Byte.toUnsignedInt(buf[index + j]) << j * 8;

			// Process MSB independently to get sign right
			if (signed)
				value |= buf[index + bytes - 1] << (bytes - 1) * 8;
			else
				value |= Byte.toUnsignedInt(buf[index + bytes - 1]) << (bytes - 1) * 8;

			return value;
		}

		/**
		 * Read a value from the BNO055
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @return Value from BNO055
		 */
		private int read(final I2C bno055) throws BNO055Exception {
			return read(bno055, 0);
		}

		/**
		 * Read a component value from a register
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @param component
		 *            Component to read
		 * @return Value from BNO055
		 */
		private int read(final I2C bno055, final int component) throws BNO055Exception {
			if (component >= components || component < 0)
				throw new IndexOutOfBoundsException(
						"Component " + component + " out of bounds for register " + toString());

			final byte[] buf = new byte[bytes];

			// Contrary to documentation return value is true on success
			if (!bno055.read(address + component * bytes, bytes, buf))
				throw new BNO055Exception("Error whilst reading value from BNO055 for register " + toString());
			return processBuf(buf, 0);
		}

		/**
		 * Read a vector of values from the BNO055
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @return Vector of values
		 */
		private int[] readVector(final I2C bno055) throws BNO055Exception {
			final byte[] buf = new byte[bytes * components];
			final int[] result = new int[components];

			// Contrary to documentation return value is true on success
			if (!bno055.read(address, bytes * components, buf))
				throw new BNO055Exception("Error whilst reading vector from BNO055 for register " + toString());

			for (int i = 0; i < components; i++)
				result[i] = processBuf(buf, i * bytes);

			return result;
		}

		/**
		 * Read a vector of doubles from BNO055
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @param scaleFactor
		 *            Scaling factor to apply to values
		 * @return Vector of values
		 */
		private double[] readVector(final I2C bno055, final double scaleFactor) throws BNO055Exception {
			final int[] rawData = readVector(bno055);
			final double[] result = new double[components];

			for (int i = 0; i < components; i++)
				result[i] = rawData[i] * scaleFactor;

			return result;
		}

		/**
		 * Write a value to the BNO055
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @param data
		 *            Value to write
		 */
		private void write(final I2C bno055, final int data) throws BNO055Exception {
			write(bno055, 0, data);
		}

		/**
		 * Write a component value to the BNO055
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @param component
		 *            Component to write
		 * @param data
		 *            Value to write
		 */
		private void write(final I2C bno055, final int component, final int data) throws BNO055Exception {
			if (component >= components || component < 0)
				throw new IndexOutOfBoundsException(
						"Component " + component + " out of bounds for register " + toString());

			for (int i = 0; i < bytes; i++)
				if (bno055.write(address + component * bytes + i, data >> i * 8 & 0x0ff))
					throw new BNO055Exception("Error whilst writing value to BNO055 for register " + toString());
		}

		/**
		 * Write a vector of values to the BNO055
		 * <p>
		 * Number of bytes written defined by register. Number of elements in
		 * vector must match.
		 *
		 * @param bno055
		 *            I2C object connected to the BNO055 chip
		 * @param vector
		 *            Values to write
		 */
		private void writeVector(final I2C bno055, final int[] vector) throws BNO055Exception {
			for (int i = 0; i < vector.length; i++)
				write(bno055, i, vector[i]);
		}
	}

	/**
	 * Object defining revision information
	 */
	public final class RevisionInfo {

		public final int accel;

		public final int mag;
		public final int gyro;
		public final int software;
		public final int bootloader;

		private RevisionInfo(final int accel, final int mag, final int gyro, final int software, final int bootloader) {
			this.accel = accel;
			this.mag = mag;
			this.gyro = gyro;
			this.software = software;
			this.bootloader = bootloader;
		}
	}

	/**
	 * Object defining system status
	 * <p>
	 * System Status (see section 4.3.58) 0 = Idle 1 = System Error 2 =
	 * Initializing Peripherals 3 = System Iniitalization 4 = Executing
	 * Self-Test 5 = Sensor fusion algorithm running 6 = System running without
	 * fusion algorithms
	 * <p>
	 * Self Test Results (see section ) 1 = test passed, 0 = test failed Bit 0 =
	 * Accelerometer self test Bit 1 = Magnetometer self test Bit 2 = Gyroscope
	 * self test Bit 3 = MCU self test 0x0F = all good!
	 * <p>
	 * System Error (see section 4.3.59) 0 = No error 1 = Peripheral
	 * initialization error 2 = System initialization error 3 = Self test result
	 * failed 4 = Register map value out of range 5 = Register map address out
	 * of range 6 = Register map write error 7 = BNO low power mode not
	 * available for selected operation mode 8 = Accelerometer power mode not
	 * available 9 = Fusion algorithm configuration error A = Sensor
	 * configuration error
	 */
	public final class SystemStatus {

		public final int systemStatus;

		public final int selfTestResult;
		public final int systemError;

		private SystemStatus(final int systemStatus, final int selfTestResult, final int systemError) {
			this.systemStatus = systemStatus;
			this.selfTestResult = selfTestResult;
			this.systemError = systemError;
		}
	}

	/**
	 * Enumeration defining the various vectors the BNO055 can provide
	 */
	public enum VectorType {
		VECTOR_ACCELEROMETER, VECTOR_MAGNETOMETER, VECTOR_GYROSCOPE, VECTOR_EULER, VECTOR_LINEARACCEL, VECTOR_GRAVITY
	}

	/**
	 * Default I2C address
	 */
	public static final int BNO055_ADDRESS_A = 0x28;

	/**
	 * Alternative I2C address
	 */
	public static final int BNO055_ADDRESS_B = 0x29;

	/**
	 * Chip ID
	 */
	private static final int BNO055_ID = 0xA0;

	private final I2C bno055;

	private OperationMode mode;

	/**
	 * Create BNO055 object at default address using I2C port
	 */
	public BNO055() {
		this(I2C.Port.kOnboard, BNO055_ADDRESS_A);
	}

	/**
	 * Create BNO055 object at given address using given port
	 *
	 * @param port
	 *            I2C port to use
	 * @param address
	 *            I2C address of chip
	 */
	private BNO055(final I2C.Port port, final int address) {
		bno055 = new I2C(port, address);
	}

	/**
	 * Create BNO055 object at given address, using onboard I2C port
	 *
	 * @param address
	 *            I2C address of chip
	 */
	public BNO055(final int address) {
		this(I2C.Port.kOnboard, address);
	}

	/**
	 * Read the current calibration state
	 * <p>
	 * Each value will be set to 0 if not calibrated and 3 if fully calibrated.
	 *
	 * @return CalibrationStatus object describing current status
	 */
	private CalibrationStatus getCalibration() throws BNO055Exception {
		final int calData = Register.CALIB_STAT.read(bno055);
		final int system = calData >> 6 & 0x03;
		final int gyro = calData >> 4 & 0x03;
		final int accel = calData >> 2 & 0x03;
		final int mag = calData & 0x03;
		return new CalibrationStatus(system, gyro, accel, mag);
	}

	/**
	 * Read a quaternion representing current orientation
	 *
	 * @return Quaternion representing current orientation
	 */
	public double[] getQuaternion() throws BNO055Exception {
		return Register.QUATERNION_DATA.readVector(bno055, 1.0 / (1 << 14));
	}

	/**
	 * Read the chip revision numbers
	 *
	 * @return RevisionInfo object with revision information
	 */
	public RevisionInfo getRevInfo() throws BNO055Exception {
		final int accel = Register.ACCEL_REV_ID.read(bno055);
		final int mag = Register.MAG_REV_ID.read(bno055);
		final int gyro = Register.GYRO_REV_ID.read(bno055);
		final int bootloader = Register.BL_REV_ID.read(bno055);
		final int software = Register.SW_REV_ID.read(bno055);
		return new RevisionInfo(accel, mag, gyro, bootloader, software);
	}

	/**
	 * Reads the sensor's offset registers
	 *
	 * @return Offset data
	 */
	public Offsets getSensorOffsets() throws BNO055Exception {
		if (isFullyCalibrated()) {
			final OperationMode lastMode = mode;
			setMode(OperationMode.OPERATION_MODE_CONFIG);
			Timer.delay(0.025);

			final int[] accelOffset = Register.ACCEL_OFFSET.readVector(bno055);
			final int[] gyroOffset = Register.GYRO_OFFSET.readVector(bno055);
			final int[] magOffset = Register.MAG_OFFSET.readVector(bno055);
			final int accelRadius = Register.ACCEL_RADIUS.read(bno055);
			final int magRadius = Register.MAG_RADIUS.read(bno055);

			setMode(lastMode);
			return new Offsets(accelOffset, gyroOffset, magOffset, accelRadius, magRadius);
		}
		return null;
	}

	/**
	 * Read the latest system status information
	 *
	 * @return SystemStatus object with current system status information
	 */
	public SystemStatus getSystemStatus() throws BNO055Exception {

		Register.PAGE_ID.write(bno055, 0);

		final int systemStatus = Register.SYS_STAT.read(bno055);
		final int selfTestResult = Register.SELFTEST_RESULT.read(bno055);
		final int systemError = Register.SYS_ERR.read(bno055);

		Timer.delay(0.2);
		return new SystemStatus(systemStatus, selfTestResult, systemError);
	}

	/**
	 * Read the temperature in degrees Celsius
	 *
	 * @return Temperature reading
	 */
	public int getTemp() throws BNO055Exception {
		return Register.TEMP.read(bno055);
	}

	/**
	 * Read a vector from the specified source
	 *
	 * @param vectorType
	 *            VectorType enumeration value to select which vector to return
	 * @return Vector representing selection
	 */
	public double[] getVector(final VectorType vectorType) throws BNO055Exception {
		/* Convert the value to an appropriate range (section 3.6.4) */
		/* and assign the value to the Vector type */
		switch (vectorType) {
		case VECTOR_MAGNETOMETER:
			/* 1uT = 16 LSB */
			return Register.MAG_DATA.readVector(bno055, 1.0 / 16.0);
		case VECTOR_GYROSCOPE:
			/* 1rps = 900 LSB */
			return Register.GYRO_DATA.readVector(bno055, 1.0 / 900.0);
		case VECTOR_EULER:
			/* 1 degree = 16 LSB */
			return Register.EULER.readVector(bno055, 1.0 / 16.0);
		case VECTOR_ACCELEROMETER:
			/* 1m/s^2 = 100 LSB */
			return Register.ACCEL_DATA.readVector(bno055, 0.001);
		case VECTOR_LINEARACCEL:
			/* 1m/s^2 = 100 LSB */
			return Register.LINEAR_ACCEL_DATA.readVector(bno055, 0.001);
		case VECTOR_GRAVITY:
			/* 1m/s^2 = 100 LSB */
			return Register.GRAVITY_DATA.readVector(bno055, 0.001);
		default:
			return new double[0];
		}
	}

	/**
	 * Initialize BNO055 to default mode
	 *
	 * @return True if initialization succeeded
	 */
	public void initialize() throws BNO055Exception {
		initialize(OperationMode.OPERATION_MODE_NDOF);
	}

	/**
	 * Initialize BNO055 to custom mode
	 *
	 * @param mode
	 *            Desired operating mode after initialization
	 * @return True if initialization succeeded
	 */
	public void initialize(final OperationMode mode) throws BNO055Exception {

		int id;

		/* Make sure we have the right device */
		id = Register.CHIP_ID.read(bno055);
		System.out.println("ChipID: " + id);
		if (id != BNO055_ID) {
			Timer.delay(1);
			id = Register.CHIP_ID.read(bno055);
			if (id != BNO055_ID)
				// Still not? Bail
				throw new BNO055Exception("BNO055 failed to initialize");
		}

		/* Switch to config mode (just in case since this is the default) */
		setMode(OperationMode.OPERATION_MODE_CONFIG);

		/* Reset */
		Register.SYS_TRIGGER.write(bno055, 0x20);
		while (Register.CHIP_ID.read(bno055) != BNO055_ID)
			Timer.delay(0.01);
		Timer.delay(0.05);

		/* Set to normal power mode */
		Register.PWR_MODE.write(bno055, PowerMode.POWER_MODE_NORMAL.value);
		Timer.delay(0.01);

		Register.PAGE_ID.write(bno055, 0);

		Register.SYS_TRIGGER.write(bno055, 0);
		Timer.delay(0.01);

		/* Set the requested operating mode (see section 3.3) */
		setMode(mode);
		Timer.delay(0.02);
	}

	/**
	 * Determine if sensor is fully calibrated
	 *
	 * @return True if sensor fully calibrated
	 */
	private boolean isFullyCalibrated() throws BNO055Exception {
		final CalibrationStatus status = getCalibration();
		return !(status.system < 3 || status.gyro < 3 || status.accel < 3 || status.mag < 3);
	}

	/**
	 * Select use of the external 32.768KHz crystal
	 *
	 * @param usextal
	 *            Use crystal when true
	 */
	public void setExtCrystalUse(final boolean usextal) throws BNO055Exception {
		final OperationMode modeback = mode;

		/* Switch to config mode (just in case since this is the default) */
		setMode(OperationMode.OPERATION_MODE_CONFIG);
		Timer.delay(0.025);
		Register.PAGE_ID.write(bno055, 0);
		if (usextal)
			Register.SYS_TRIGGER.write(bno055, 0x80);
		else
			Register.SYS_TRIGGER.write(bno055, 0x00);
		Timer.delay(0.01);
		/* Set the requested operating mode (see section 3.3) */
		setMode(modeback);
		Timer.delay(0.02);
	}

	/**
	 * Puts the chip in the specified operating mode
	 *
	 * @param mode
	 *            OperationMode enumeration value selecting operating mode
	 */
	private void setMode(final OperationMode mode) throws BNO055Exception {
		this.mode = mode;
		Register.OPR_MODE.write(bno055, mode.value);
		Timer.delay(0.03);
	}

	/**
	 * Writes to the sensor's offset registers from an offset object
	 *
	 * @param offsets
	 *            Offsets data from call to getSensorOffsets
	 */
	public void setSensorOffsets(final Offsets offsets) throws BNO055Exception {
		final OperationMode lastMode = mode;
		setMode(OperationMode.OPERATION_MODE_CONFIG);
		Timer.delay(0.025);

		Register.ACCEL_OFFSET.writeVector(bno055, offsets.accelOffset);
		Register.GYRO_OFFSET.writeVector(bno055, offsets.gyroOffset);
		Register.MAG_OFFSET.writeVector(bno055, offsets.magOffset);
		Register.ACCEL_RADIUS.write(bno055, offsets.accelRadius);
		Register.MAG_RADIUS.write(bno055, offsets.magRadius);

		setMode(lastMode);
	}
}
