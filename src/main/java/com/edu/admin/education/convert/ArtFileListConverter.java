package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtFileListCommand;
import com.edu.admin.education.dto.ArtFileListDto;
import com.edu.admin.education.model.ArtFileList;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtFileListCommand to ArtFileList转换类
 * @author mengqa
 * @date 2019-12-19
 */
public class ArtFileListConverter {

    /**
     * Convert ArtFileListCommand to ArtFileList
     * @param artFileListCommand
     * @return
     */
    public static ArtFileList convertToArtFileList(ArtFileListCommand artFileListCommand) {
        if (artFileListCommand == null) {
            return null;
        }
        ArtFileList artFileList = new ArtFileList();

        artFileList.setUrl(artFileListCommand.getUrl());
        artFileList.setTitle(artFileListCommand.getTitle());
        artFileList.setContent(artFileListCommand.getContent());

        return artFileList;
    }

    /**
     * Convert ArtFileListCommand to ArtFileList
     * @return
     */
    public static ArtFileListDto convertToArtFileListDto(ArtFileList artFileList) {
        if (artFileList == null) {
            return null;
        }
        ArtFileListDto artFileListDto = new ArtFileListDto();

        artFileListDto.setUrl(artFileList.getUrl());
        artFileListDto.setTitle(artFileList.getTitle());
        artFileListDto.setContent(artFileList.getContent());
        artFileListDto.setCreatetime(artFileList.getCreatetime());
        artFileListDto.setUpdatetime(artFileList.getUpdatetime());

        artFileListDto.setId(artFileList.getId());
        return artFileListDto;
    }

    /**
     * Convert ArtFileList to ArtFileListCommand
     * @param artFileList
     * @return
     */
    public static ArtFileListCommand convertToArtFileListCommand(ArtFileList artFileList) {
        if (artFileList == null) {
            return null;
        }
        ArtFileListCommand artFileListCommand = new ArtFileListCommand();


        return artFileListCommand;
    }

    /**
     * 批量Convert ArtFileListCommand convertToList ArtFileList
     * @param artFileListCommandList
     * @return
     */
     public static List<ArtFileList> convertToListArtFileList(List<ArtFileListCommand> artFileListCommandList) {
        List<ArtFileList> list = new ArrayList<>();
        artFileListCommandList.forEach(ArtFileListCommand -> list.add(convertToArtFileList(ArtFileListCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtFileList convertToList ArtFileListCommand
     * @param artFileListList
     * @return
     */
     public static List<ArtFileListCommand> convertToListArtFileListCommand(List<ArtFileList> artFileListList) {
        List<ArtFileListCommand> list = new ArrayList<>();
        artFileListList.forEach(artFileList -> list.add(convertToArtFileListCommand(artFileList)));
        return list;
    }

    /**
     * 批量Convert  ArtFileList convertToList ArtFileListCommand
     * @param artFileListList
     * @return
     */
    public static List<ArtFileListDto> convertToListArtFileListDto(List<ArtFileList> artFileListList) {
        List<ArtFileListDto> list = new ArrayList<>();
        artFileListList.forEach(artFileList -> list.add(convertToArtFileListDto(artFileList)));
        return list;
    }
}
