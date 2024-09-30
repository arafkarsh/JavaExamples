/**
 * Copyright (c) 2024 Araf Karsh Hamid
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package java18;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * JavaExamples / CustomDNSResolverProvider
 *
 * Place the file "java.net.spi.InetAddressResolverProvider"  in the
 * resources/META-INF/services folder.
 * Content of the file "java18.CustomDNSResolverProvider"
 *
     project-root/
     ├── src/
     │    └── main/
     │         └── java/
     │              └── java18/
     │                    └── CustomInetAddressResolverProvider.java
     ├── resources/
           └── META-INF/
                 └── services/
                       └── java.net.spi.InetAddressResolverProvider
   OR
     src/
     ├── main/
     │   ├── java/
     │   │    └── your/package/name/
     │   │          └── CustomInetAddressResolverProvider.java
     │   └── resources/
     │         └── META-INF/
     │               └── services/
     │                     └── java.net.spi.InetAddressResolverProvider
  *
 * If the JVM doesn't pick up the file then right-click on the "resources" folder and click the
 * option "Mark Director as" Resource Root.
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-30T07:54
 */
public class CustomDNSResolverProvider extends InetAddressResolverProvider {
    @Override
    public InetAddressResolver get(Configuration configuration) {
        return new CustomInetAddressResolver();
    }

    /**
     * {@return the name of this provider, or {@code null} if unnamed}
     */
    @Override
    public String name() {
        return "";
    }

    static class CustomInetAddressResolver implements InetAddressResolver {

        @Override
        public Stream<InetAddress> lookupByName(String host, LookupPolicy lookupPolicy) throws UnknownHostException {
            try {
                // System.out.println("Inside CustomDNSResolverProvider..... ");
                // Custom rule: return a fixed IP address for any domain ending in ".fusion"
                if (host.endsWith(".fusion")) {
                    return Stream.of(InetAddress.getByName("10.18.36.54"));
                } else {
                    // Fallback to the default resolution mechanism
                    // Bypass recursion for default resolution mechanism
                    return lookupUsingJNDI(host);
                }
            } catch (Exception e) {
                System.out.println("Host Not Found "+host+" ERROR: "+e.getMessage());
                // e.printStackTrace();
                return Stream.empty();
            }
        }

        /**
         * 1.	Proper Attribute Handling:
         * 	 	The Attributes object from the JNDI context is properly parsed.
         * 	 	The “A” record is for IPv4 addresses.
         * 	 	The “AAAA” record is for IPv6 addresses.
         * 	 	The code checks if these attributes exist and iterates over their values.
         * 	2.	Trim Strings:
         * 	 	The IP address strings retrieved are trimmed using .trim() before passing them to
         * 	    InetAddress.getByName() to ensure there are no extra spaces that can cause parsing
         * 	    issues.
         * 	3.	Improved Error Handling:
         * 	 	Each potential DNS record (IPv4 or IPv6) is individually checked and converted to
         * 	    an InetAddress. Any parsing issues will be caught, but won’t stop the entire lookup
         * 	    process.
         *
         * @param host
         * @return
         */
        private Stream<InetAddress> lookupUsingJNDI(String host) {
            List<InetAddress> addressList = new ArrayList<>();
            try {
                DirContext ictx = new InitialDirContext();
                Attributes attrs = ictx.getAttributes("dns:/" + host);

                // Attempt to retrieve both IPv4 ("A") and IPv6 ("AAAA") records
                Attribute aRecord = attrs.get("A");
                Attribute aaaaRecord = attrs.get("AAAA");

                // Process A records (IPv4)
                if (aRecord != null) {
                    for (int i = 0; i < aRecord.size(); i++) {
                        String ip = (String) aRecord.get(i);
                        try {
                            InetAddress address = InetAddress.getByName(ip.trim());
                            addressList.add(address);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Process AAAA records (IPv6)
                if (aaaaRecord != null) {
                    for (int i = 0; i < aaaaRecord.size(); i++) {
                        String ip = (String) aaaaRecord.get(i);
                        try {
                            InetAddress address = InetAddress.getByName(ip.trim());
                            addressList.add(address);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (NamingException e) {
                e.printStackTrace();
            }
            return addressList.stream();
        }

        @Override
        public String lookupByAddress(byte[] addr) throws UnknownHostException {
            try {
                // Fallback to default reverse lookup mechanism
                InetAddress address = InetAddress.getByAddress(addr);
                return address.getHostName();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
