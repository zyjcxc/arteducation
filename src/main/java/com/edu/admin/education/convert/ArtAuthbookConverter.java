package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtAuthbookCommand;
import com.edu.admin.education.dto.ArtAuthbookDto;
import com.edu.admin.education.model.ArtAuthbook;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtAuthbookCommand to ArtAuthbook转换类
 * @author mengqa
 * @date 2019-11-08
 */
public class ArtAuthbookConverter {

    /**
     * Convert ArtAuthbookCommand to ArtAuthbook
     * @param artAuthbookCommand
     * @return
     */
    public static ArtAuthbook convertToArtAuthbook(ArtAuthbookCommand artAuthbookCommand) {
        if (artAuthbookCommand == null) {
            return null;
        }
        ArtAuthbook artAuthbook = new ArtAuthbook();

        artAuthbook.setContent(artAuthbookCommand.getContent());
        artAuthbook.setTitle(artAuthbookCommand.getTitle());
        artAuthbook.setState(artAuthbookCommand.getState());
        artAuthbook.setPicurl(artAuthbookCommand.getPicurl());

        return artAuthbook;
    }

    /**
     * Convert ArtAuthbookCommand to ArtAuthbook
     * @return
     */
    public static ArtAuthbookDto convertToArtAuthbookDto(ArtAuthbook artAuthbook) {
        if (artAuthbook == null) {
            return null;
        }
        ArtAuthbookDto artAuthbookDto = new ArtAuthbookDto();

        artAuthbookDto.setContent(artAuthbook.getContent());
        artAuthbookDto.setTitle(artAuthbook.getTitle());
        artAuthbookDto.setState(artAuthbook.getState());
        artAuthbookDto.setCreatetime(artAuthbook.getCreatetime());
        artAuthbookDto.setUpdatetime(artAuthbook.getUpdatetime());
        artAuthbookDto.setId(artAuthbook.getId());
        artAuthbookDto.setPicurl(artAuthbook.getPicurl());

        return artAuthbookDto;
    }

    /**
     * Convert ArtAuthbook to ArtAuthbookCommand
     * @param artAuthbook
     * @return
     */
    public static ArtAuthbookCommand convertToArtAuthbookCommand(ArtAuthbook artAuthbook) {
        if (artAuthbook == null) {
            return null;
        }
        ArtAuthbookCommand artAuthbookCommand = new ArtAuthbookCommand();


        return artAuthbookCommand;
    }

    /**
     * 批量Convert ArtAuthbookCommand convertToList ArtAuthbook
     * @param artAuthbookCommandList
     * @return
     */
     public static List<ArtAuthbook> convertToListArtAuthbook(List<ArtAuthbookCommand> artAuthbookCommandList) {
        List<ArtAuthbook> list = new ArrayList<>();
        artAuthbookCommandList.forEach(ArtAuthbookCommand -> list.add(convertToArtAuthbook(ArtAuthbookCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtAuthbook convertToList ArtAuthbookCommand
     * @param artAuthbookList
     * @return
     */
     public static List<ArtAuthbookCommand> convertToListArtAuthbookCommand(List<ArtAuthbook> artAuthbookList) {
        List<ArtAuthbookCommand> list = new ArrayList<>();
        artAuthbookList.forEach(artAuthbook -> list.add(convertToArtAuthbookCommand(artAuthbook)));
        return list;
    }

    /**
     * 批量Convert  ArtAuthbook convertToList ArtAuthbookCommand
     * @param artAuthbookList
     * @return
     */
    public static List<ArtAuthbookDto> convertToListArtAuthbookDto(List<ArtAuthbook> artAuthbookList) {
        List<ArtAuthbookDto> list = new ArrayList<>();
        artAuthbookList.forEach(artAuthbook -> list.add(convertToArtAuthbookDto(artAuthbook)));
        return list;
    }
}
