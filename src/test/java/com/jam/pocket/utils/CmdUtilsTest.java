package com.jam.pocket.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jam on 2017/1/11.
 */
public class CmdUtilsTest {

    @Test
    public void testRunPythonConvert() throws Exception {
        String sourceFilePath = "src/test/resource/tabelog_note.csv";
        String targetFilePath = "src/test/resource/tabelog_note_result.csv";
        CmdUtils.runPythonConvert(sourceFilePath, targetFilePath);
    }

}