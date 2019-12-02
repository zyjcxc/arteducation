package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtGuestInfoCommand;
import com.edu.admin.education.dto.ArtGuestInfoDto;
import com.edu.admin.education.model.ArtGuestInfo;

import java.util.ArrayList;
import java.util.List;

/**ArtGuestInfo
 * Command to ArtGuestInfo转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtGuestInfoConverter {

    /**
     * Convert ArtGuestInfoCommand to ArtGuestInfo
     * @param artGuestInfoCommand
     * @return
     */
    public static ArtGuestInfo convertToArtGuestInfo(ArtGuestInfoCommand artGuestInfoCommand) {
        if (artGuestInfoCommand == null) {
            return null;
        }
        ArtGuestInfo artGuestInfo = new ArtGuestInfo();

        artGuestInfo.setAddress(artGuestInfoCommand.getAddress());
        artGuestInfo.setEmail(artGuestInfoCommand.getEmail());
        artGuestInfo.setMessage(artGuestInfoCommand.getMessage());
        artGuestInfo.setName(artGuestInfoCommand.getName());
        artGuestInfo.setPhone(artGuestInfoCommand.getPhone());

        return artGuestInfo;
    }

    /**
     * Convert ArtGuestInfoCommand to ArtGuestInfo
     * @return
     */
    public static ArtGuestInfoDto convertToArtGuestInfoDto(ArtGuestInfo artGuestInfo) {
        if (artGuestInfo == null) {
            return null;
        }
        ArtGuestInfoDto artGuestInfoDto = new ArtGuestInfoDto();

        artGuestInfoDto.setAddress(artGuestInfo.getAddress());
        artGuestInfoDto.setEmail(artGuestInfo.getEmail());
        artGuestInfoDto.setMessage(artGuestInfo.getMessage());
        artGuestInfoDto.setName(artGuestInfo.getName());
        artGuestInfoDto.setPhone(artGuestInfo.getPhone());
        artGuestInfoDto.setCreatetime(artGuestInfo.getCreatetime());
        artGuestInfoDto.setUpdatetime(artGuestInfo.getUpdatetime());
        artGuestInfoDto.setId(artGuestInfo.getId());
        return artGuestInfoDto;
    }

    /**
     * Convert ArtGuestInfo to ArtGuestInfoCommand
     * @param artGuestInfo
     * @return
     */
    public static ArtGuestInfoCommand convertToArtGuestInfoCommand(ArtGuestInfo artGuestInfo) {
        if (artGuestInfo == null) {
            return null;
        }
        ArtGuestInfoCommand artGuestInfoCommand = new ArtGuestInfoCommand();


        return artGuestInfoCommand;
    }

    /**
     * 批量Convert ArtGuestInfoCommand convertToList ArtGuestInfo
     * @param artGuestInfoCommandList
     * @return
     */
     public static List<ArtGuestInfo> convertToListArtGuestInfo(List<ArtGuestInfoCommand> artGuestInfoCommandList) {
        List<ArtGuestInfo> list = new ArrayList<>();
        artGuestInfoCommandList.forEach(ArtGuestInfoCommand -> list.add(convertToArtGuestInfo(ArtGuestInfoCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtGuestInfo convertToList ArtGuestInfoCommand
     * @param artGuestInfoList
     * @return
     */
     public static List<ArtGuestInfoCommand> convertToListArtGuestInfoCommand(List<ArtGuestInfo> artGuestInfoList) {
        List<ArtGuestInfoCommand> list = new ArrayList<>();
        artGuestInfoList.forEach(artGuestInfo -> list.add(convertToArtGuestInfoCommand(artGuestInfo)));
        return list;
    }

    /**
     * 批量Convert  ArtGuestInfo convertToList ArtGuestInfoCommand
     * @param artGuestInfoList
     * @return
     */
    public static List<ArtGuestInfoDto> convertToListArtGuestInfoDto(List<ArtGuestInfo> artGuestInfoList) {
        List<ArtGuestInfoDto> list = new ArrayList<>();
        artGuestInfoList.forEach(artGuestInfo -> list.add(convertToArtGuestInfoDto(artGuestInfo)));
        return list;
    }
}
