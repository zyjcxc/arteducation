package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtTextbookTypeCommand;
import com.edu.admin.education.dto.ArtTextbookTypeDto;
import com.edu.admin.education.model.ArtTextbookType;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtTextbookTypeTypeCommand to ArtTextbookTypeType转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtTextbookTypeConverter {

    /**
     * Convert ArtTextbookTypeCommand to ArtTextbookType
     * @param artTextbookTypeCommand
     * @return
     */
    public static ArtTextbookType convertToArtTextbookType(ArtTextbookTypeCommand artTextbookTypeCommand) {
        if (artTextbookTypeCommand == null) {
            return null;
        }
        ArtTextbookType artTextbookType = new ArtTextbookType();

        artTextbookType.setName(artTextbookTypeCommand.getName());
        artTextbookType.setState(artTextbookTypeCommand.getState());

        return artTextbookType;
    }

    /**
     * Convert ArtTextbookTypeCommand to ArtTextbookType
     * @return
     */
    public static ArtTextbookTypeDto convertToArtTextbookTypeDto(ArtTextbookType artTextbookType) {
        if (artTextbookType == null) {
            return null;
        }
        ArtTextbookTypeDto artTextbookTypeDto = new ArtTextbookTypeDto();

        artTextbookTypeDto.setName(artTextbookType.getName());
        artTextbookTypeDto.setState(artTextbookType.getState());
        artTextbookTypeDto.setCreatetime(artTextbookType.getCreatetime());
        artTextbookTypeDto.setUpdatetime(artTextbookType.getUpdatetime());
        artTextbookTypeDto.setId(artTextbookType.getId());
        return artTextbookTypeDto;
    }

    /**
     * Convert ArtTextbookType to ArtTextbookTypeCommand
     * @param artTextbookType
     * @return
     */
    public static ArtTextbookTypeCommand convertToArtTextbookTypeCommand(ArtTextbookType artTextbookType) {
        if (artTextbookType == null) {
            return null;
        }
        ArtTextbookTypeCommand artTextbookTypeCommand = new ArtTextbookTypeCommand();


        return artTextbookTypeCommand;
    }

    /**
     * 批量Convert ArtTextbookTypeCommand convertToList ArtTextbookType
     * @param artTextbookTypeCommandList
     * @return
     */
     public static List<ArtTextbookType> convertToListArtTextbookType(List<ArtTextbookTypeCommand> artTextbookTypeCommandList) {
        List<ArtTextbookType> list = new ArrayList<>();
        artTextbookTypeCommandList.forEach(ArtTextbookTypeCommand -> list.add(convertToArtTextbookType(ArtTextbookTypeCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtTextbookType convertToList ArtTextbookTypeCommand
     * @param artTextbookTypeList
     * @return
     */
     public static List<ArtTextbookTypeCommand> convertToListArtTextbookTypeCommand(List<ArtTextbookType> artTextbookTypeList) {
        List<ArtTextbookTypeCommand> list = new ArrayList<>();
        artTextbookTypeList.forEach(artTextbookType -> list.add(convertToArtTextbookTypeCommand(artTextbookType)));
        return list;
    }

    /**
     * 批量Convert  ArtTextbookType convertToList ArtTextbookTypeCommand
     * @param artTextbookTypeList
     * @return
     */
    public static List<ArtTextbookTypeDto> convertToListArtTextbookTypeDto(List<ArtTextbookType> artTextbookTypeList) {
        List<ArtTextbookTypeDto> list = new ArrayList<>();
        artTextbookTypeList.forEach(artTextbookType -> list.add(convertToArtTextbookTypeDto(artTextbookType)));
        return list;
    }
}
