package com.jam.pocket.parser;

import java.util.List;

/**
 * Created by Jam on 2016/12/29.
 */
public interface Parser<T> {

    public List<T> parseCsvFile(String filePath) throws Exception;

}
