package com.zx.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 删除文件和目录
 *
 */
public class DeleteFileUtil {

    private static Logger logger = LoggerFactory.getLogger(DeleteFileUtil.class);
    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String path,String fileName) {
        File file = new File(path,fileName);
        logger.info(path+fileName);
        if (!file.exists()) {
            logger.info("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            return deleteFile(path,fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String path,String fileName) {
        File file = new File(path,fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                logger.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            logger.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


}