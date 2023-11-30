package com.sndshun.file.convert;

import com.sndshun.commons.tools.DateUtils;
import com.sndshun.file.vo.minio.MinioBucketVo;
import io.minio.messages.Item;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

/**
 * Minio相关的属性转换
 *
 * @author mapleie
 */
public class MinioConvert {

    /**
     * item转换Minio桶VO
     *
     * @param item Item
     * @return MinioBucketVo
     */
    public static MinioBucketVo itemToMinioBucketVo(Item item) {
        MinioBucketVo minioBucketVo = new MinioBucketVo();
        String etag = item.etag();
        String objectName = item.objectName();
        ZonedDateTime zonedDateTime = item.lastModified();
        long size = item.size();
        String storageClass = item.storageClass();
        boolean latest = item.isLatest();
        String versionId = item.versionId();
        Map<String, String> userMetadata = item.userMetadata();
        boolean dir = item.isDir();
        String creationDate = DateUtils.dateToStringYyyyMMddHHMmSs(DateUtils.toDate(zonedDateTime));
        minioBucketVo.setEtag(etag).setObjectName(objectName)
                .setZonedDateTime(creationDate).setSize(size).
                setStorageClass(storageClass).setLatest(latest).
                setVersionId(versionId).setUserMetadata(userMetadata)
                .setDir(dir);
        return minioBucketVo;
    }
}
