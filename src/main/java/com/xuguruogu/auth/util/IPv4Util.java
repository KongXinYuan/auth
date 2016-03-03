package com.xuguruogu.auth.util;

import java.net.InetAddress;

public class IPv4Util {

	private final static int INADDRSZ = 4;
	private final static int DEFAULTIP = 0;

	public static byte[] ipToBytesByInet(String ipAddr) {
		try {
			return InetAddress.getByName(ipAddr).getAddress();
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * 把IP地址转化为int
	 *
	 * @param ipAddr
	 * @return int
	 */
	public static byte[] ipToBytesByReg(String ipAddr) {
		byte[] ret = new byte[4];
		try {
			String[] ipArr = ipAddr.split("\\.");
			ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
			return ret;
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}

	}

	/**
	 * 根据位运算把 byte[] -> int
	 *
	 * @param bytes
	 * @return int
	 */
	public static int bytesToInt(byte[] bytes) {
		int addr = bytes[3] & 0xFF;
		addr |= ((bytes[2] << 8) & 0xFF00);
		addr |= ((bytes[1] << 16) & 0xFF0000);
		addr |= ((bytes[0] << 24) & 0xFF000000);
		return addr;
	}

	/**
	 * 把IP地址转化为int
	 *
	 * @param ipAddr
	 * @return int
	 */
	public static int ipToInt(String ipAddr) {
		try {
			return bytesToInt(ipToBytesByInet(ipAddr));
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * ipInt -> byte[]
	 *
	 * @param ipInt
	 * @return byte[]
	 */
	public static byte[] intToBytes(int ipInt) {
		byte[] ipAddr = new byte[INADDRSZ];
		ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
		ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
		ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
		ipAddr[3] = (byte) (ipInt & 0xFF);
		return ipAddr;
	}

	/**
	 * 把int->ip地址
	 *
	 * @param ipInt
	 * @return String
	 */
	public static String intToIp(int ipInt) {
		return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.').append((ipInt >> 16) & 0xff).append('.')
				.append((ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff)).toString();
	}

	public static int ipToIntWithDefault(String ipAddr) {
		try {
			return bytesToInt(ipToBytesByInet(ipAddr));
		} catch (Exception e) {
			return DEFAULTIP;
		}
	};

	public static String intToIpWithDefault(int ipInt) {
		if (DEFAULTIP == ipInt) {
			return "0";
		}
		return intToIp(ipInt);
	};

}
