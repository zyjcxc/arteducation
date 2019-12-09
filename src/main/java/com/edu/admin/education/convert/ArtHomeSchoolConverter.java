package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtHomeSchoolCommand;
import com.edu.admin.education.dto.ArtHomeSchoolDto;
import com.edu.admin.education.model.ArtHomeSchool;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtHomeSchoolCommand to ArtHomeSchool转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtHomeSchoolConverter {

    /**
     * Convert ArtHomeSchoolCommand to ArtHomeSchool
     * @param artHomeSchoolCommand
     * @return
     */
    public static ArtHomeSchool convertToArtHomeSchool(ArtHomeSchoolCommand artHomeSchoolCommand) {
        if (artHomeSchoolCommand == null) {
            return null;
        }
        ArtHomeSchool artHomeSchool = new ArtHomeSchool();

        artHomeSchool.setPicurl(artHomeSchoolCommand.getPicurl());
        artHomeSchool.setSort(artHomeSchoolCommand.getSort());
        artHomeSchool.setName(artHomeSchoolCommand.getName());
        artHomeSchool.setRecommend(artHomeSchoolCommand.getRecommend());
        artHomeSchool.setContent(artHomeSchoolCommand.getContent());

        return artHomeSchool;
    }

    /**
     * Convert ArtHomeSchoolCommand to ArtHomeSchool
     * @return
     */
    public static ArtHomeSchoolDto convertToArtHomeSchoolDto(ArtHomeSchool artHomeSchool) {
        if (artHomeSchool == null) {
            return null;
        }
        ArtHomeSchoolDto artHomeSchoolDto = new ArtHomeSchoolDto();

        artHomeSchoolDto.setContent(artHomeSchool.getContent());
        artHomeSchoolDto.setPicurl(artHomeSchool.getPicurl());
        artHomeSchoolDto.setSort(artHomeSchool.getSort());
        artHomeSchoolDto.setCreatetime(artHomeSchool.getCreatetime());
        artHomeSchoolDto.setUpdatetime(artHomeSchool.getUpdatetime());
        artHomeSchoolDto.setName(artHomeSchool.getName());
        artHomeSchoolDto.setRecommend(artHomeSchool.getRecommend());

        artHomeSchoolDto.setId(artHomeSchool.getId());
        return artHomeSchoolDto;
    }

    /**
     * Convert ArtHomeSchool to ArtHomeSchoolCommand
     * @param artHomeSchool
     * @return
     */
    public static ArtHomeSchoolCommand convertToArtHomeSchoolCommand(ArtHomeSchool artHomeSchool) {
        if (artHomeSchool == null) {
            return null;
        }
        ArtHomeSchoolCommand artHomeSchoolCommand = new ArtHomeSchoolCommand();


        return artHomeSchoolCommand;
    }

    /**
     * 批量Convert ArtHomeSchoolCommand convertToList ArtHomeSchool
     * @param artHomeSchoolCommandList
     * @return
     */
     public static List<ArtHomeSchool> convertToListArtHomeSchool(List<ArtHomeSchoolCommand> artHomeSchoolCommandList) {
        List<ArtHomeSchool> list = new ArrayList<>();
        artHomeSchoolCommandList.forEach(ArtHomeSchoolCommand -> list.add(convertToArtHomeSchool(ArtHomeSchoolCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtHomeSchool convertToList ArtHomeSchoolCommand
     * @param artHomeSchoolList
     * @return
     */
     public static List<ArtHomeSchoolCommand> convertToListArtHomeSchoolCommand(List<ArtHomeSchool> artHomeSchoolList) {
        List<ArtHomeSchoolCommand> list = new ArrayList<>();
        artHomeSchoolList.forEach(artHomeSchool -> list.add(convertToArtHomeSchoolCommand(artHomeSchool)));
        return list;
    }

    /**
     * 批量Convert  ArtHomeSchool convertToList ArtHomeSchoolCommand
     * @param artHomeSchoolList
     * @return
     */
    public static List<ArtHomeSchoolDto> convertToListArtHomeSchoolDto(List<ArtHomeSchool> artHomeSchoolList) {
        List<ArtHomeSchoolDto> list = new ArrayList<>();
        artHomeSchoolList.forEach(artHomeSchool -> list.add(convertToArtHomeSchoolDto(artHomeSchool)));
        return list;
    }
}
