package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtBannerInfoCommand;
import com.edu.admin.education.dto.ArtBannerInfoDto;
import com.edu.admin.education.model.ArtBannerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtBannerInfoCommand to ArtBannerInfo转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtBannerInfoConverter {

    /**
     * Convert ArtBannerInfoCommand to ArtBannerInfo
     * @param artBannerInfoCommand
     * @return
     */
    public static ArtBannerInfo convertToArtBannerInfo(ArtBannerInfoCommand artBannerInfoCommand) {
        if (artBannerInfoCommand == null) {
            return null;
        }
        ArtBannerInfo artBannerInfo = new ArtBannerInfo();

        artBannerInfo.setPicurl(artBannerInfoCommand.getPicurl());
        artBannerInfo.setSite(artBannerInfoCommand.getSite());
        artBannerInfo.setSort(artBannerInfoCommand.getSort());
        artBannerInfo.setUrl(artBannerInfoCommand.getUrl());
        artBannerInfo.setState(artBannerInfoCommand.getState());

        artBannerInfo.setTitle(artBannerInfoCommand.getTitle());
        artBannerInfo.setRecommend(artBannerInfoCommand.getRecommend());


        return artBannerInfo;
    }

    /**
     * Convert ArtBannerInfoCommand to ArtBannerInfo
     * @return
     */
    public static ArtBannerInfoDto convertToArtBannerInfoDto(ArtBannerInfo artBannerInfo) {
        if (artBannerInfo == null) {
            return null;
        }
        ArtBannerInfoDto artBannerInfoDto = new ArtBannerInfoDto();

        artBannerInfoDto.setPicurl(artBannerInfo.getPicurl());
        artBannerInfoDto.setSite(artBannerInfo.getSite());
        artBannerInfoDto.setSort(artBannerInfo.getSort());
        artBannerInfoDto.setUrl(artBannerInfo.getUrl());
        artBannerInfoDto.setState(artBannerInfo.getState());
        artBannerInfoDto.setCreatetime(artBannerInfo.getCreatetime());
        artBannerInfoDto.setUpdatetime(artBannerInfo.getUpdatetime());

        artBannerInfoDto.setTitle(artBannerInfo.getTitle());
        artBannerInfoDto.setRecommend(artBannerInfo.getRecommend());

        artBannerInfoDto.setId(artBannerInfo.getId());
        return artBannerInfoDto;
    }

    /**
     * Convert ArtBannerInfo to ArtBannerInfoCommand
     * @param artBannerInfo
     * @return
     */
    public static ArtBannerInfoCommand convertToArtBannerInfoCommand(ArtBannerInfo artBannerInfo) {
        if (artBannerInfo == null) {
            return null;
        }
        ArtBannerInfoCommand artBannerInfoCommand = new ArtBannerInfoCommand();


        return artBannerInfoCommand;
    }

    /**
     * 批量Convert ArtBannerInfoCommand convertToList ArtBannerInfo
     * @param artBannerInfoCommandList
     * @return
     */
     public static List<ArtBannerInfo> convertToListArtBannerInfo(List<ArtBannerInfoCommand> artBannerInfoCommandList) {
        List<ArtBannerInfo> list = new ArrayList<>();
        artBannerInfoCommandList.forEach(ArtBannerInfoCommand -> list.add(convertToArtBannerInfo(ArtBannerInfoCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtBannerInfo convertToList ArtBannerInfoCommand
     * @param artBannerInfoList
     * @return
     */
     public static List<ArtBannerInfoCommand> convertToListArtBannerInfoCommand(List<ArtBannerInfo> artBannerInfoList) {
        List<ArtBannerInfoCommand> list = new ArrayList<>();
        artBannerInfoList.forEach(artBannerInfo -> list.add(convertToArtBannerInfoCommand(artBannerInfo)));
        return list;
    }

    /**
     * 批量Convert  ArtBannerInfo convertToList ArtBannerInfoCommand
     * @param artBannerInfoList
     * @return
     */
    public static List<ArtBannerInfoDto> convertToListArtBannerInfoDto(List<ArtBannerInfo> artBannerInfoList) {
        List<ArtBannerInfoDto> list = new ArrayList<>();
        artBannerInfoList.forEach(artBannerInfo -> list.add(convertToArtBannerInfoDto(artBannerInfo)));
        return list;
    }
}
