/**
 * Copyright (c) 2018 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation

 *   or (per the licensee's choosing)

 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package java05.ip;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * IPAddress object converts IP (String) to a long for faster access. This object
 * allows to hold a range of IP Address. Port number (with range) also can be assigned.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public final class IPAddress implements java.io.Serializable, Comparable {

	private static final int DEFAULT_NETMASK = 32;		// 255.255.255.255
	public final static String DEFAULT_NETMASK_STRING = "255.255.255.0";
	private final BitSet bits = new BitSet(5);			// To validate the IP Address
	
	private static final String VALIDVALUES = "128, 192, 224, 240, 248, 252";
	 
    public static final int CLASS_A 	= 0 ;
    public static final int CLASS_B 	= 1 ;
    public static final int CLASS_C 	= 2 ;
    public static final int CLASS_D 	= 3 ;
    public static final int MULTI_CAST 	= 3 ;
    
	// Private Address Space
	// Range of IP Addresses Class of Networks Number of Networks
	// 10.0.0.0 to 10.255.255.255 A 1
	// 172.16.0.0 to 172.31.255.255 B 16
	// 192.168.0.0 to 192.168.255.255 C 256

	//	class A PrivateIP range <167772160,184549375>
	//	class B PrivateIP range <2886729728,2887778303>
	//	class C PrivateIP range <3232235520,3232301055>

	private static final long PRIVATE_STARTIP_A = 167772160l;
	private static final long PRIVATE_ENDIP_A 	= 184549375l;
	private static final long PRIVATE_STARTIP_B = 2886729728l;
	private static final long PRIVATE_ENDIP_B 	= 2887778303l;
	private static final long PRIVATE_STARTIP_C = 3232235520l;
	private static final long PRIVATE_ENDIP_C 	= 3232301055l;
    
    public static final long CLASS_A_START 		= IPAddress.convertToLong("1.0.0.0") ; 			// 1.0.0.0
    public static final long CLASS_A_END 		= IPAddress.convertToLong("127.255.255.255"); 	// 127.255.255.255
    public static final long CLASS_B_START 		= IPAddress.convertToLong("128.0.0.0"); 		// 128.0.0.0
    public static final long CLASS_B_END 		= IPAddress.convertToLong("192.255.255.255"); 	// 191.255.255.255
    public static final long CLASS_C_START 		= IPAddress.convertToLong("192.0.0.0"); 		// 
    public static final long CLASS_C_END 		= IPAddress.convertToLong("192.255.255.255"); 	// 191.255.255.255
    public static final long CLASS_D_START 		= IPAddress.convertToLong("224.0.0.0"); 		// 
    public static final long CLASS_D_END 		= IPAddress.convertToLong("239.255.255.255"); 	// 
    public static final long MULTICAST_START 	= IPAddress.convertToLong("224.0.0.0"); 		// 
    public static final long MULTICAST_END 		= IPAddress.convertToLong("239.255.255.255"); 	// 

	private final String IP;
	private final String IP_TO;
	private final long ipaddress;
	private long maxIPRange;
	private int port;
	private int maxPort;
	private int netmask = DEFAULT_NETMASK ;
	private boolean ipRangeAvailable = false;
	private boolean portRangeAvailable = false;
	
	private IPAddress startHostIp = null;
	private IPAddress endHostIp = null;

	
	/**
	 * Empty Constructor to be used only when u want to convert IP (String) to long
	 * and avoid creating IPAddress object every time when u want to do a conversion.
	 */
	public IPAddress() {
		IP 	  = "";
		IP_TO	  = "";
		ipaddress = -1;
		netmask   = DEFAULT_NETMASK ;
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to long
	 * if its a ipaddress rather than a host name.
	 * 
	 * @param _ip
	 */
	public IPAddress(String _ip) {
		if(_ip != null) {
			IP	  = _ip;
			ipaddress = convert(_ip);
		} else {
			IP 	  = "";
			ipaddress = -1;
		}
		IP_TO	   = "";
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to long
	 * if its a ipaddress rather than a host name.
	 * 
	 * @param  _ip, int _port
	 */
	public IPAddress(String _ip, int _port) {
		this(_ip);
		this.port = _port;
	}

	
	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to long
	 * if its a ipaddress rather than a host name. Second parameter is to set IP range.
	 * 
	 * @param  _ipStart, _ipEnd
	 */
	public IPAddress(String _ipStart, String _ipEnd) {
		if( (_ipStart != null) && (_ipEnd != null) ) {
			IP	   = _ipStart;
			IP_TO	   = _ipEnd;
			ipaddress  = convert(_ipStart);
			maxIPRange = convert(_ipEnd);
			if(ipaddress > maxIPRange) {
				ipRangeAvailable = false;
			}
			else {
				ipRangeAvailable = true;
			}
		} else {
			IP 	   = "";
			IP_TO	   = "";
			ipaddress  = -1;
		}
	}

	/**
	 * IPAddress Constructor. Accepts string as a ip address (ipv4) converts to long
	 * if its a ipaddress rather than a host name. Second parameter is to set IP range.
	 * 
	 * @param  _ipStart, _ipEnd,  _port
	 */
	public IPAddress(String _ipStart, String _ipEnd, int _port) {
		this(_ipStart, _ipEnd);
		this.port = _port;
	}

	/**
	 * Set the max port value for the range
	 *
	 * @param  _maxport
	 */
	public void setMaxPortValue(final int _maxport) {
		this.maxPort = _maxport;
		if(port > maxPort) {
			portRangeAvailable = false;
		}
		else {
			portRangeAvailable = true;
		}
	}

	public boolean isPrivateClassA() {
		if (ipaddress >= PRIVATE_STARTIP_A && ipaddress <= PRIVATE_ENDIP_A) {
			return true;
		}
		return false;
	}
	
	public boolean isPrivateClassB() {
		if (ipaddress >= PRIVATE_STARTIP_B && ipaddress <= PRIVATE_ENDIP_B) {
			return true;
		}
		return false;
	}
	
	public boolean isPrivateClassC() {
		if (ipaddress >= PRIVATE_STARTIP_C && ipaddress <= PRIVATE_ENDIP_C) {
			return true;
		}
		return false;
	}
	
	public boolean isPrivateIP() {
		
		if (isPrivateClassA() || isPrivateClassB() || isPrivateClassC()) {
			return true;
		}
		
		return false;
	}
    
    public boolean isClassA()
    {
       return ((ipaddress >= CLASS_A_START) && (ipaddress <=CLASS_A_END)); 
    }
    
    public boolean isClassB()
    {
       return ((ipaddress >= CLASS_B_START) && (ipaddress <=CLASS_B_END)); 
    }
    
    public boolean isClassC()
    {
       return ((ipaddress >= CLASS_C_START) && (ipaddress <=CLASS_C_END)); 
    }
    
    public boolean isClassD()
    {
       return ((ipaddress >= CLASS_D_START) && (ipaddress <=CLASS_D_END)); 
    }
    
    public boolean isMultiCast()
    {
        return isClassD();
    }
	
	public static boolean isPrivateIP(long _ipaddress) {
		if ( (_ipaddress >= PRIVATE_STARTIP_A && _ipaddress <= PRIVATE_ENDIP_A) ||
			 (_ipaddress >= PRIVATE_STARTIP_B && _ipaddress <= PRIVATE_ENDIP_B) ||
			 (_ipaddress >= PRIVATE_STARTIP_C && _ipaddress <= PRIVATE_ENDIP_C) ) {
			return true;
		}
		
		return false;		
	}

	public static boolean isValidIPAddress(String _ipAddress) {
		long ipaddress = convertToLong(_ipAddress);
		return isValidIPAddress(ipaddress);
	}
	
	/**
	 * Check whether valid ip address. Returns true if valid.
	 *
	 * @return boolean
	 */
	public static boolean isValidIPAddress(long _ipaddress) {
		return (_ipaddress > -1) ? true : false;			
	}
	
	/**
	 * Check whether valid ip address. Returns true if valid.
	 *
	 * @return boolean
	 */
	public boolean isValidIPAddress() {
		return (ipaddress > -1) ? true : false;			
	}

	/**
	 * Returns True if IP range available. (Default is false)
	 *
	 * @return boolean
	 */
	public boolean isIPRangeAvailable() {
		return ipRangeAvailable;
	}

	/**
	 * Returns True if Port range available. (Default is false)
	 *
	 * @return boolean
	 */
	public boolean isPortRangeAvailable() {
		return portRangeAvailable;
	}

	/**
	 * Returns the IP Max range if its set.
	 *
	 * @return String
	 */
	public String getIPAddressMaxRange() {
		return IP_TO;
	}

	/**
	 * Returns the IP Max Range as a long value
	 *
	 * @return long
	 */
	public long getIPAddressMaxRangeAsLong() {
		return maxIPRange;
	}

	/**
	 * Get the IP Address 
	 *
	 * @return String ipaddress
	 */
	public String getIPAddress() {
		return IP;
	}

	/**
	 * Get the IP Address as a long value
	 *
	 * @return long ipaddress
	 */
	public long getIPAddressAsLong() {
		return ipaddress;
	}

	/**
	 * Get the port (Initial port value if a max value is set)
	 *	
	 * @return int port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Get the max port value (Initial port value if a max value is set)
	 *	
	 * @return int port
	 */
	public int getPortMaxRange() {
		return maxPort;
	}
	
	/**
	 * Check the port range.
	 * 
	 * @param _port
	 * @return boolean
	 */
	public boolean betweenPortRange(final int _port) {
		return ((_port >= this.port) && (_port <= this.maxPort));
	}

	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param _ipAddress
	 * @return boolean
	 */
	public boolean betweenIPRange(final IPAddress _ipAddress) {
		if(_ipAddress == null) { return false; }
		return this.betweenIPRange(_ipAddress.ipaddress);
	}
	
	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param  _ipAddress
	 * @return boolean
	 */
	public boolean betweenIPRange(final long _ipAddress) {
		return ((_ipAddress >= this.ipaddress) && (_ipAddress <= this.maxIPRange));
	}
	
	/**
	 * Check IP Range. Returns true if found
	 *
	 * @param  _ipAddress
	 * @return boolean
	 */
	public boolean betweenIPRange(final String _ipAddress) {
		return betweenIPRange(convert(_ipAddress));
	}

	public static final long convertToLong(final String _ipAddress) {
		if(_ipAddress == null) {
			return -1;
		}
		BitSet bits = new BitSet(5);
		int netmask = DEFAULT_NETMASK;
		long elements[] = new long[5];
		elements[4]	= DEFAULT_NETMASK;	// 255.255.255.255

		StringTokenizer st = new StringTokenizer(_ipAddress,"./");
		for(byte i=0;i<5;i++) {
			try {
				elements[i] = Long.parseLong(st.nextToken());
				if( (elements[i] > -1) && (elements[i] < 256) ) {
					bits.set(i);
				}
			}
			catch(NoSuchElementException ignored) {}
			catch(Exception e) { return -1; }
		}

		// return -1 if not a valid IP Address
		if(!bits.get(0) || !bits.get(1) || !bits.get(2) || !bits.get(3)) {
			return -1;
		}

		netmask = (int) elements[4];

		/**
		 * Logic for converting IP String to long (Bit operation is 3 times faster than multiplication).
		 * 
		 * Example. 146.127.98.12
		 * a = 146 * 256 * 256 * 256;
		 * b = 127 * 256 * 256;
		 * c = 98  * 256;
		 * d = 12
		 * 
		 * ipAddress = a + b + c + d;
		 */
		return ((elements[0] << 24) + (elements[1] << 16) + (elements[2] << 8) + (elements[3]));
	
	}
	
	/**
	 * Convert IP Address (eg. 146.127.98.100 to 2457821796 ) from String to a long.
	 * 
	 * @param  _ipAddress
	 * @return long ip
	 */
	public final long convert(final String _ipAddress) {

		if(_ipAddress == null) {
			return -1;
		}
		bits.clear();
		long elements[] = new long[5];
		elements[4]	= DEFAULT_NETMASK;	// 255.255.255.255

		StringTokenizer st = new StringTokenizer(_ipAddress,"./");
		for(byte i=0;i<5;i++) {
			try {
				elements[i] = Long.parseLong(st.nextToken());
				if( (elements[i] > -1) && (elements[i] < 256) ) {
					bits.set(i);
				}
			}
			catch(NoSuchElementException ignored) {}
			catch(Exception e) { return -1; }
		}

		// return -1 if not a valid IP Address
		if(!bits.get(0) || !bits.get(1) || !bits.get(2) || !bits.get(3)) {
			return -1;
		}

		netmask = (int) elements[4];

		/**
		 * Logic for converting IP String to long (Bit operation is 3 times faster than multiplication).
		 * 
		 * Eg. 146.127.98.12
		 * a = 146 * 256 * 256 * 256;
		 * b = 127 * 256 * 256;
		 * c = 98  * 256;
		 * d = 12
		 * 
		 * ipAddress = a + b + c + d;
		 */
		return ((elements[0] << 24) + (elements[1] << 16) + (elements[2] << 8) + (elements[3]));
	}

	/**
	 * Convert a long (number) to an ip address. For eg. 2457821796 to 146.127.98.100
	 *
	 * @param  ip
	 * @return String
	 */
	public static final String convertBack(final long ip) {
		// 4294967295 = 255.255.255.255
		if( (ip < 0) || (ip > 4294967295l) ) {
			return "";
		}
		if(ip == 0) {
			return "0.0.0.0";
		}
		String binary = Long.toBinaryString(ip);
		int startx, endx, totalsize;
		totalsize = binary.length();
		endx      = totalsize;
		int ipBlock[] = new int[4];	
		for(int b = 3, block = 8; b >= 0; b--) {
			startx = totalsize - block;
			if(startx < 0) startx = 0;
			ipBlock[b] = Long.valueOf(binary.substring(startx, endx), 2).intValue();
			endx = startx;
			block += 8;
		
		}
		return new StringBuilder()
				.append(ipBlock[0])
				.append(".").append(ipBlock[1])
				.append(".").append(ipBlock[2])
				.append(".").append(ipBlock[3])
				.toString();
	}


        /**
         * Convert a netmask to the string notation. eg. 32 to 255.255.255.0
         *
         * @param  mask
         * @return String
         */
        public static String convertNetworkmask(int mask) {

                if(mask <= 0 || mask > 32){
                        return DEFAULT_NETMASK_STRING;
                }

                int array[] = new int[4];
                int noBits = 0;
                noBits = mask / 8;
                int i=0;

                for(i=0; i<noBits; i++){
                     array[i] = 255;
                }

	             int rem = mask % 8;
	             if (rem > 0) {
	             	array[i] = getHighBitValue(rem);
	             }

                String dot = ".";
                StringBuilder sb = new StringBuilder();
                sb.append(array[0]).append(dot).append(array[1]).append(dot);
                sb.append(array[2]).append(dot).append(array[3]);
                return sb.toString();

        }


	/**
         * Converts ip mask notation to the int value. eg.  255.255.255.0 to 32
         *
         * @param  _ipNotationMask
         * @return String
         */
        public static int getNetworkMask(String _ipNotationMask) throws Exception{

                if(_ipNotationMask == null){
                        return 0;
                }

                int array[] = new int[4];
                int mask = 0;
                try{
                        StringTokenizer st = new StringTokenizer(_ipNotationMask,".");
                        array[0] = Integer.parseInt(st.nextToken());
                        array[1] = Integer.parseInt(st.nextToken());
                        array[2] = Integer.parseInt(st.nextToken());
                        array[3] = Integer.parseInt(st.nextToken());

                        int quad = 0;
                        for(int i=0; i<4; i++){
                                if(array[i] < 255){
                                        for(int j=i+1; j<4; j++){
                                                if(array[j] > 0){
                                                        return 0;
                                                }
                                        }
                                }
                                else{
                                        quad++;
                                }
                        }
                        if(quad < 4){
                                mask = (quad * 8) + getQuadValue(array[quad]);
                        }
                        else{
                                mask = (quad * 8);
                        }
                }
                catch(Exception ex){
                        throw new Exception("Invalid Mask Value, Valid values are " + VALIDVALUES);

                }
                return mask;
	}

	/**
	 * Get the network mask
	 *	
	 * @return int
	 */
	public final int getNetworkMask() {
		return netmask;
	}

	/**
	 * Get the hash code of IPAddress (String)
	 *
	 * @return int
	 */
	public int hashCode(){
		return IP.hashCode();
	}

	/**
	 * Returns the IP Address (String)
	 *
	 * @return String
	 */
	public String toString() {
		return IP;
	}

	/**
	 * Implements the Comparable interface
	 */
	public int compareTo(Object o) {
		return compareTo((IPAddress)o);
	}

	/**
	 * Implements the Comparable interface
	 */
	public int compareTo(IPAddress ip) {
		return (int) (this.ipaddress - ip.ipaddress);
	}

	/**
	 * Implementation of equals
	 *
	 * @return boolean.
	 */

	public boolean equals(Object o) {
		try {
			if(o instanceof String) {
				return IP.equals((String)o);
			} else if (o instanceof IPAddress) {
				return (this.ipaddress == (((IPAddress)o).ipaddress));
			}
		} catch (Exception ignored) { }
		return false;
	}

	public final boolean compareIPAddress(IPAddress ip) {
		if(ip == null) return false;
		if(isIPRangeAvailable()) {
			if(betweenIPRange(ip.ipaddress)) {	
				return comparePort(ip.port);
			}
		} else if (this.ipaddress == ip.ipaddress) {
			return comparePort(ip.port);
		}
		return false;
	}
	
	private boolean comparePort(int p) {
		if(port == 0) return true;				// Port check not required
		if(isPortRangeAvailable()) {
			return betweenPortRange(p);
		}
		return (port == p);
	}

	public static int getHighBitValue(int bits){
                if(bits > 7){
                        return 255;
                }
                int value = 0;
                for(int i=7;i> (7-bits);i--){
                        value = value + Double.valueOf(Math.pow(2,i)).intValue();
                }
                return value;
        }

	private static int getQuadValue(int quadValue) throws Exception{
                if(quadValue > 255){
                        return 8;
                }
                switch(quadValue){
                        case 0:     return 0;
                        case 128:  return 1;
                        case 192:  return 2;
                        case 224:  return 3;
                        case 240:  return 4;
                        case 248:  return 5;
                        case 252:  return 6;
                        case 254:  return 7;
                        default:
                                throw new Exception("Invalid Value");
                }
        }


	public void setNetworkMask(String net) throws Exception{
		netmask = getNetworkMask(net);
	}
	
	public IPAddress getStartHostIp() throws Exception{
		if(startHostIp == null){
			throw new Exception("Start Ip not set");
		}
		return startHostIp;
	}
	
	public void setStartHostIp(IPAddress ip){
		this.startHostIp = ip;
	}
	
	public IPAddress getEndHostIp() throws Exception{
		if(endHostIp == null){
			throw new Exception("End Ip not set");
		}
		return endHostIp;
	}

	public void setEndHostIp(IPAddress ip){
		this.endHostIp = ip;
	}
	
	public static IPAddress getHostRange(String networkAddress, 
					String netmask) throws Exception{
		IPAddress ip = new IPAddress(networkAddress);
		if(!ip.isValidIPAddress()){
			throw new Exception("Invalid IP Address");
		}	
	
		long ipLong = ip.getIPAddressAsLong();
		ip.setNetworkMask(netmask); // Netmask is Invalid if this statement generates an Exception

		long mask = ip.convert(netmask);
		mask = (int)~mask;
		long host = mask & ipLong; // And Op on network Address and inverted mask yeilds host part
		long startIp = ipLong - host + 1; 	// substract number of host part from 	
						  	// IpAddress specified to get start Host

		long hosts = (long)(Math.pow(2.0, (32 - ip.getNetworkMask(netmask))));
		if(hosts < 3){
			throw new Exception("Invalid Mask Value, Valid values are " + VALIDVALUES);
		}
		long endIp = startIp + hosts - 3; 
		ip.setStartHostIp(new IPAddress(ip.convertBack(startIp)));
		ip.setEndHostIp(new IPAddress(ip.convertBack(endIp)));
		return ip;
	
	}

	/**
	 * This main method is only for testing purposes.
	 */

	public static void main(String[] args) {

		System.out.println("Testing IP Address Utility");
		System.out.println("----------------------------------------------------------------------------------");

		// 10.0.0.0 to 10.255.255.255 A 1
		// 172.16.0.0 to 172.31.255.255 B 16
		// 192.168.0.0 to 192.168.255.255 C 256

		IPAddress privateIP1 = new IPAddress("10.0.0.0");
		IPAddress privateIP2 = new IPAddress("10.255.255.255");
		IPAddress privateIP3 = new IPAddress("172.16.0.0");
		IPAddress privateIP4 = new IPAddress("172.31.255.255");
		IPAddress privateIP5 = new IPAddress("192.168.0.0");
		IPAddress privateIP6 = new IPAddress("192.168.255.255");
		
		System.out.println("class A PrivateIP range <"+privateIP1.getIPAddressAsLong()+", "+privateIP2.getIPAddressAsLong()+">");
		System.out.println("class B PrivateIP range <"+privateIP3.getIPAddressAsLong()+", "+privateIP4.getIPAddressAsLong()+">");
		System.out.println("class C PrivateIP range <"+privateIP5.getIPAddressAsLong()+", "+privateIP6.getIPAddressAsLong()+">");
		
		System.out.println("----------------------------------------------------------------------------------");

		IPAddress ip1 = new IPAddress("0.0.0.0");
		IPAddress ip2 = new IPAddress("10.54.32.125");
		IPAddress ip3 = new IPAddress("127.0.0.1");
		IPAddress ip4 = new IPAddress("132.129.198.48");
		IPAddress ip5 = new IPAddress("172.17.2.211");
		IPAddress ip6 = new IPAddress("192.168.1.1");
		IPAddress ip7 = new IPAddress("255.255.255.255");
		IPAddress ip8 = new IPAddress("256.1.1.1");

		System.out.println("String = "+ip1+"\t\tLong = "+ip1.getIPAddressAsLong()+"          Binary = "+Long.toBinaryString(ip1.getIPAddressAsLong())+"                                BACK = "+IPAddress.convertBack(ip1.getIPAddressAsLong()));
		System.out.println("String = "+ip2+"\t\tLong = "+ip2.getIPAddressAsLong()+"  Binary = "+Long.toBinaryString(ip2.getIPAddressAsLong())+"     BACK = "+IPAddress.convertBack(ip2.getIPAddressAsLong()));
		System.out.println("String = "+ip3+"\t\tLong = "+ip3.getIPAddressAsLong()+" Binary = "+Long.toBinaryString(ip3.getIPAddressAsLong())+"  BACK = "+IPAddress.convertBack(ip3.getIPAddressAsLong()));
		System.out.println("String = "+ip4+"\t\tLong = "+ip4.getIPAddressAsLong()+" Binary = "+Long.toBinaryString(ip4.getIPAddressAsLong())+" BACK = "+IPAddress.convertBack(ip4.getIPAddressAsLong()));
		System.out.println("String = "+ip5+"\t\tLong = "+ip5.getIPAddressAsLong()+" Binary = "+Long.toBinaryString(ip5.getIPAddressAsLong())+" BACK = "+IPAddress.convertBack(ip5.getIPAddressAsLong()));
		System.out.println("String = "+ip6+"\t\tLong = "+ip6.getIPAddressAsLong()+" Binary = "+Long.toBinaryString(ip6.getIPAddressAsLong())+" BACK = "+IPAddress.convertBack(ip6.getIPAddressAsLong()));
		System.out.println("String = "+ip7+"\tLong = "+ip7.getIPAddressAsLong()+" Binary = "+Long.toBinaryString(ip7.getIPAddressAsLong())+" BACK = "+IPAddress.convertBack(ip7.getIPAddressAsLong()));
		System.out.println("String = "+ip8+"\t\tLong = "+ip8.getIPAddressAsLong());
		System.out.println("----------------------------------------------------------------------------------");

	}	
}