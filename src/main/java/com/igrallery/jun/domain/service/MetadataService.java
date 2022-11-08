package com.igrallery.jun.domain.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.igrallery.jun.domain.entity.Image;
import com.igrallery.jun.domain.entity.Metadata;
import com.igrallery.jun.domain.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetadataService {

    private final MetadataRepository metadataRepository;

    public Metadata parse (String imgPath) throws IOException, ImageProcessingException {
        Metadata dto = null;
        Resource target = new FileSystemResource(imgPath);

        if (target.exists()) {
            com.drew.metadata.Metadata metadata = ImageMetadataReader.readMetadata(target.getFile());
            Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

            if (directory != null) {
                dto = new Metadata();
                dto.setDate(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL).toString());
                dto.setDevice(directory.getString(ExifSubIFDDirectory.TAG_MODEL));

                //GPS 가져오기
                if (gpsDirectory != null) {
                    if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {
                        dto.setLat(gpsDirectory.getGeoLocation().getLatitude());
                        dto.setLon(gpsDirectory.getGeoLocation().getLongitude());
                    }
                }
            }
        }
        return dto;
    }

    public void apply (Image image) throws ImageProcessingException, IOException {
        Metadata entity = parse(image.getPath());
        if (entity != null) {
            entity.setImage(image);
            entity.setName(image.getOriginalName());
            metadataRepository.save(entity);
        }
    }
}
