package com.jam.pocket.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jam on 2017/1/11.
 */
public class CmdUtils {

    private final static Logger logger = Logger.getLogger(CmdUtils.class);
    private static final String SCRIPT_PATH = "script/crawler/crawler.py";
    public static final int STATUS_OK = 0;

    public static Boolean runPythonConvert(String sourcePath, String targetPath) {
        logger.debug("Start run python.");
        int exitCode = -1;
        String[] cmd = new String[4];
        cmd[0] = "python"; // check version of installed python: python -V
        cmd[1] = SCRIPT_PATH;
        cmd[2] = "-s" + sourcePath;
        cmd[3] = "-t" + targetPath;
        BufferedReader stdInput = null;
        BufferedReader stdError = null;
        try {
            // create runtime to execute external command
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor();
            exitCode = proc.exitValue();

            // retrieve output from python script
            stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            logger.debug("Below is output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                logger.debug(s);
            }

            // read any errors from the attempted command
            logger.debug("Below is output of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                logger.debug(s);
            }
            logger.debug("Run python exit code:" + exitCode);
        } catch (IOException | InterruptedException e) {
            logger.error("Run python convert file failed.", e);
        } finally {
            try {
                stdInput.close();
                stdError.close();
            } catch (IOException e) {
                logger.error("Close BufferedReader failed.", e);
            }

            return exitCode == STATUS_OK;
        }
    }

}
