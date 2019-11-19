package com.he;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote{
    User getUser() throws RemoteException;
}
