package com.he.invoke;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

public class HDFSConnector {
    public static FileSystem getCon() throws Exception{
            String url = "hdfs://192.168.200.200:9000";
            Configuration configuration = new Configuration();
            configuration.set("fs.hdfs.impl",org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//            configuration.set("dfs.replication","3");
            FileSystem fs = FileSystem.get(URI.create(url), configuration,"root");
            return fs;
    }
}
