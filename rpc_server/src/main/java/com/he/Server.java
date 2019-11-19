package com.he;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        //创建RMI的注册类
        try {
            Registry registry = LocateRegistry.createRegistry(8888);
            ///将需要提供出去的类进行连接
            IServer service=new IServerImpl();

            registry.bind("hello",service);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }


    }
}
