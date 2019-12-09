package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtHomeStudentCommand;
import com.edu.admin.education.dto.ArtHomeStudentDto;
import com.edu.admin.education.model.ArtHomeStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtHomeStudentCommand to ArtHomeStudent转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtHomeStudentConverter {

    /**
     * Convert ArtHomeStudentCommand to ArtHomeStudent
     * @param artHomeStudentCommand
     * @return
     */
    public static ArtHomeStudent convertToArtHomeStudent(ArtHomeStudentCommand artHomeStudentCommand) {
        if (artHomeStudentCommand == null) {
            return null;
        }
        ArtHomeStudent artHomeStudent = new ArtHomeStudent();

        artHomeStudent.setPicurl(artHomeStudentCommand.getPicurl());
        artHomeStudent.setSort(artHomeStudentCommand.getSort());
        artHomeStudent.setName(artHomeStudentCommand.getName());
        artHomeStudent.setRecommend(artHomeStudentCommand.getRecommend());
        artHomeStudent.setContent(artHomeStudentCommand.getContent());

        return artHomeStudent;
    }

    /**
     * Convert ArtHomeStudentCommand to ArtHomeStudent
     * @return
     */
    public static ArtHomeStudentDto convertToArtHomeStudentDto(ArtHomeStudent artHomeStudent) {
        if (artHomeStudent == null) {
            return null;
        }
        ArtHomeStudentDto artHomeStudentDto = new ArtHomeStudentDto();

        artHomeStudentDto.setContent(artHomeStudent.getContent());
        artHomeStudentDto.setPicurl(artHomeStudent.getPicurl());
        artHomeStudentDto.setSort(artHomeStudent.getSort());
        artHomeStudentDto.setCreatetime(artHomeStudent.getCreatetime());
        artHomeStudentDto.setUpdatetime(artHomeStudent.getUpdatetime());
        artHomeStudentDto.setName(artHomeStudent.getName());
        artHomeStudentDto.setRecommend(artHomeStudent.getRecommend());

        artHomeStudentDto.setId(artHomeStudent.getId());
        return artHomeStudentDto;
    }

    /**
     * Convert ArtHomeStudent to ArtHomeStudentCommand
     * @param artHomeStudent
     * @return
     */
    public static ArtHomeStudentCommand convertToArtHomeStudentCommand(ArtHomeStudent artHomeStudent) {
        if (artHomeStudent == null) {
            return null;
        }
        ArtHomeStudentCommand artHomeStudentCommand = new ArtHomeStudentCommand();


        return artHomeStudentCommand;
    }

    /**
     * 批量Convert ArtHomeStudentCommand convertToList ArtHomeStudent
     * @param artHomeStudentCommandList
     * @return
     */
     public static List<ArtHomeStudent> convertToListArtHomeStudent(List<ArtHomeStudentCommand> artHomeStudentCommandList) {
        List<ArtHomeStudent> list = new ArrayList<>();
        artHomeStudentCommandList.forEach(ArtHomeStudentCommand -> list.add(convertToArtHomeStudent(ArtHomeStudentCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtHomeStudent convertToList ArtHomeStudentCommand
     * @param artHomeStudentList
     * @return
     */
     public static List<ArtHomeStudentCommand> convertToListArtHomeStudentCommand(List<ArtHomeStudent> artHomeStudentList) {
        List<ArtHomeStudentCommand> list = new ArrayList<>();
        artHomeStudentList.forEach(artHomeStudent -> list.add(convertToArtHomeStudentCommand(artHomeStudent)));
        return list;
    }

    /**
     * 批量Convert  ArtHomeStudent convertToList ArtHomeStudentCommand
     * @param artHomeStudentList
     * @return
     */
    public static List<ArtHomeStudentDto> convertToListArtHomeStudentDto(List<ArtHomeStudent> artHomeStudentList) {
        List<ArtHomeStudentDto> list = new ArrayList<>();
        artHomeStudentList.forEach(artHomeStudent -> list.add(convertToArtHomeStudentDto(artHomeStudent)));
        return list;
    }
}
