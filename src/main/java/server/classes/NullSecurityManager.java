package server.classes;

import java.io.FileDescriptor;
import java.net.InetAddress;


public class NullSecurityManager extends SecurityManager {
  public void checkCreateClassLoader() { } 
  public void checkAccess(Thread g) { }
  public void checkAccess(ThreadGroup g) { }
  public void checkExit(int status) { }
  public void checkExec(String cmd) { }
  public void checkLink(String lib) { }
  public void checkRead(FileDescriptor fd) { }
  public void checkRead(String file) { }
  public void checkRead(String file, Object context) { }
  public void checkWrite(FileDescriptor fd) { }
  public void checkWrite(String file) { }
  public void checkDelete(String file) { }
  public void checkConnect(String host, int port) { }
  public void checkConnect(String host, int port, Object context) { }
  public void checkListen(int port) { }
  public void checkAccept(String host, int port) { }
  public void checkMulticast(InetAddress maddr) { }
  public void checkPropertiesAccess() { }
  public void checkPropertyAccess(String key) { }
  public void checkPrintJobAccess() { }
  public void checkPackageAccess(String pkg) { }
  public void checkPackageDefinition(String pkg) { }
  public void checkSetFactory() { }
  public void checkSecurityAccess(String provider) { }
}	

