package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtTextbookCommand;
import com.edu.admin.education.dto.ArtTextbookDto;
import com.edu.admin.education.model.ArtTextbook;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtTextbookCommand to ArtTextbook转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtTextbookConverter {

    /**
     * Convert ArtTextbookCommand to ArtTextbook
     * @param artTextbookCommand
     * @return
     */
    public static ArtTextbook convertToArtTextbook(ArtTextbookCommand artTextbookCommand) {
        if (artTextbookCommand == null) {
            return null;
        }
        ArtTextbook artTextbook = new ArtTextbook();

        artTextbook.setAuthor(artTextbookCommand.getAuthor());
        artTextbook.setContent(artTextbookCommand.getContent());
        artTextbook.setTitle(artTextbookCommand.getTitle());
        artTextbook.setVersion(artTextbookCommand.getVersion());
        artTextbook.setTextbookTypeId(artTextbookCommand.getTextbookTypeId());
        artTextbook.setState(artTextbookCommand.getState());

        return artTextbook;
    }

    /**
     * Convert ArtTextbookCommand to ArtTextbook
     * @return
     */
    public static ArtTextbookDto convertToArtTextbookDto(ArtTextbook artTextbook) {
        if (artTextbook == null) {
            return null;
        }
        ArtTextbookDto artTextbookDto = new ArtTextbookDto();

        artTextbookDto.setAuthor(artTextbook.getAuthor());
        artTextbookDto.setContent(artTextbook.getContent());
        artTextbookDto.setTitle(artTextbook.getTitle());
        artTextbookDto.setVersion(artTextbook.getVersion());
        artTextbookDto.setTextbookTypeId(artTextbook.getTextbookTypeId());
        artTextbookDto.setState(artTextbook.getState());
        artTextbookDto.setCreatetime(artTextbook.getCreatetime());
        artTextbookDto.setUpdatetime(artTextbook.getUpdatetime());
        artTextbookDto.setId(artTextbook.getId());
        return artTextbookDto;
    }

    /**
     * Convert ArtTextbook to ArtTextbookCommand
     * @param artTextbook
     * @return
     */
    public static ArtTextbookCommand convertToArtTextbookCommand(ArtTextbook artTextbook) {
        if (artTextbook == null) {
            return null;
        }
        ArtTextbookCommand artTextbookCommand = new ArtTextbookCommand();


        return artTextbookCommand;
    }

    /**
     * 批量Convert ArtTextbookCommand convertToList ArtTextbook
     * @param artTextbookCommandList
     * @return
     */
     public static List<ArtTextbook> convertToListArtTextbook(List<ArtTextbookCommand> artTextbookCommandList) {
        List<ArtTextbook> list = new ArrayList<>();
        artTextbookCommandList.forEach(ArtTextbookCommand -> list.add(convertToArtTextbook(ArtTextbookCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtTextbook convertToList ArtTextbookCommand
     * @param artTextbookList
     * @return
     */
     public static List<ArtTextbookCommand> convertToListArtTextbookCommand(List<ArtTextbook> artTextbookList) {
        List<ArtTextbookCommand> list = new ArrayList<>();
        artTextbookList.forEach(artTextbook -> list.add(convertToArtTextbookCommand(artTextbook)));
        return list;
    }

    /**
     * 批量Convert  ArtTextbook convertToList ArtTextbookCommand
     * @param artTextbookList
     * @return
     */
    public static List<ArtTextbookDto> convertToListArtTextbookDto(List<ArtTextbook> artTextbookList) {
        List<ArtTextbookDto> list = new ArrayList<>();
        artTextbookList.forEach(artTextbook -> list.add(convertToArtTextbookDto(artTextbook)));
        return list;
    }
}
