package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtTeacherCommand;
import com.edu.admin.education.dto.ArtTeacherDto;
import com.edu.admin.education.model.ArtTeacher;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtTeacherCommand to ArtTeacher转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtTeacherConverter {

    /**
     * Convert ArtTeacherCommand to ArtTeacher
     * @param artTeacherCommand
     * @return
     */
    public static ArtTeacher convertToArtTeacher(ArtTeacherCommand artTeacherCommand) {
        if (artTeacherCommand == null) {
            return null;
        }
        ArtTeacher artTeacher = new ArtTeacher();

        artTeacher.setContent(artTeacherCommand.getContent());
        artTeacher.setName(artTeacherCommand.getName());
        artTeacher.setNamePy(artTeacherCommand.getNamePy());
        artTeacher.setPhotoUrl(artTeacherCommand.getPhotoUrl());
        artTeacher.setState(artTeacherCommand.getState());
        artTeacher.setPosition(artTeacherCommand.getPosition());
        artTeacher.setSex(artTeacherCommand.getSex());

        artTeacher.setTitle(artTeacherCommand.getTitle());
        artTeacher.setPhone(artTeacherCommand.getPhone());
        artTeacher.setSchool(artTeacherCommand.getSchool());

        return artTeacher;
    }

    /**
     * Convert ArtTeacherCommand to ArtTeacher
     * @return
     */
    public static ArtTeacherDto convertToArtTeacherDto(ArtTeacher artTeacher) {
        if (artTeacher == null) {
            return null;
        }
        ArtTeacherDto artTeacherDto = new ArtTeacherDto();
        artTeacherDto.setPhotoUrl(artTeacher.getPhotoUrl());
        artTeacherDto.setSex(artTeacher.getSex());
        artTeacherDto.setName(artTeacher.getName());
        artTeacherDto.setNamePy(artTeacher.getNamePy());
        artTeacherDto.setPosition(artTeacher.getPosition());
        artTeacherDto.setContent(artTeacher.getContent());
        artTeacherDto.setState(artTeacher.getState());
        artTeacherDto.setCreatetime(artTeacher.getCreatetime());
        artTeacherDto.setUpdatetime(artTeacher.getUpdatetime());
        artTeacherDto.setId(artTeacher.getId());

        artTeacherDto.setTitle(artTeacher.getTitle());
        artTeacherDto.setPhone(artTeacher.getPhone());
        artTeacherDto.setSchool(artTeacher.getSchool());

        return artTeacherDto;
    }

    /**
     * Convert ArtTeacher to ArtTeacherCommand
     * @param artTeacher
     * @return
     */
    public static ArtTeacherCommand convertToArtTeacherCommand(ArtTeacher artTeacher) {
        if (artTeacher == null) {
            return null;
        }
        ArtTeacherCommand artTeacherCommand = new ArtTeacherCommand();


        return artTeacherCommand;
    }

    /**
     * 批量Convert ArtTeacherCommand convertToList ArtTeacher
     * @param artNewsCommandList
     * @return
     */
     public static List<ArtTeacher> convertToListArtTeacher(List<ArtTeacherCommand> artNewsCommandList) {
        List<ArtTeacher> list = new ArrayList<>();
        artNewsCommandList.forEach(ArtTeacherCommand -> list.add(convertToArtTeacher(ArtTeacherCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtTeacher convertToList ArtTeacherCommand
     * @param artNewsList
     * @return
     */
     public static List<ArtTeacherCommand> convertToListArtTeacherCommand(List<ArtTeacher> artNewsList) {
        List<ArtTeacherCommand> list = new ArrayList<>();
        artNewsList.forEach(artTeacher -> list.add(convertToArtTeacherCommand(artTeacher)));
        return list;
    }

    /**
     * 批量Convert  ArtTeacher convertToList ArtTeacherCommand
     * @param artNewsList
     * @return
     */
    public static List<ArtTeacherDto> convertToListArtTeacherDto(List<ArtTeacher> artNewsList) {
        List<ArtTeacherDto> list = new ArrayList<>();
        artNewsList.forEach(artTeacher -> list.add(convertToArtTeacherDto(artTeacher)));
        return list;
    }
}
