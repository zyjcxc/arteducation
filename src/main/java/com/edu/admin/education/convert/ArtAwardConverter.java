package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtAwardCommand;
import com.edu.admin.education.dto.ArtAwardDto;
import com.edu.admin.education.model.ArtAward;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtAwardCommand to ArtAward转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtAwardConverter {

    /**
     * Convert ArtAwardCommand to ArtAward
     * @param artAwardCommand
     * @return
     */
    public static ArtAward convertToArtAward(ArtAwardCommand artAwardCommand) {
        if (artAwardCommand == null) {
            return null;
        }
        ArtAward artAward = new ArtAward();

        artAward.setContent(artAwardCommand.getContent());
        artAward.setTitle(artAwardCommand.getTitle());
        artAward.setState(artAwardCommand.getState());
        artAward.setPicurl(artAwardCommand.getPicurl());

        return artAward;
    }

    /**
     * Convert ArtAwardCommand to ArtAward
     * @return
     */
    public static ArtAwardDto convertToArtAwardDto(ArtAward artAward) {
        if (artAward == null) {
            return null;
        }
        ArtAwardDto artAwardDto = new ArtAwardDto();

        artAwardDto.setContent(artAward.getContent());
        artAwardDto.setTitle(artAward.getTitle());
        artAwardDto.setState(artAward.getState());
        artAwardDto.setCreatetime(artAward.getCreatetime());
        artAwardDto.setUpdatetime(artAward.getUpdatetime());
        artAwardDto.setId(artAward.getId());
        artAwardDto.setPicurl(artAward.getPicurl());

        return artAwardDto;
    }

    /**
     * Convert ArtAward to ArtAwardCommand
     * @param artAward
     * @return
     */
    public static ArtAwardCommand convertToArtAwardCommand(ArtAward artAward) {
        if (artAward == null) {
            return null;
        }
        ArtAwardCommand artAwardCommand = new ArtAwardCommand();


        return artAwardCommand;
    }

    /**
     * 批量Convert ArtAwardCommand convertToList ArtAward
     * @param artAwardCommandList
     * @return
     */
     public static List<ArtAward> convertToListArtAward(List<ArtAwardCommand> artAwardCommandList) {
        List<ArtAward> list = new ArrayList<>();
        artAwardCommandList.forEach(ArtAwardCommand -> list.add(convertToArtAward(ArtAwardCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtAward convertToList ArtAwardCommand
     * @param artAwardList
     * @return
     */
     public static List<ArtAwardCommand> convertToListArtAwardCommand(List<ArtAward> artAwardList) {
        List<ArtAwardCommand> list = new ArrayList<>();
        artAwardList.forEach(artAward -> list.add(convertToArtAwardCommand(artAward)));
        return list;
    }

    /**
     * 批量Convert  ArtAward convertToList ArtAwardCommand
     * @param artAwardList
     * @return
     */
    public static List<ArtAwardDto> convertToListArtAwardDto(List<ArtAward> artAwardList) {
        List<ArtAwardDto> list = new ArrayList<>();
        artAwardList.forEach(artAward -> list.add(convertToArtAwardDto(artAward)));
        return list;
    }
}
