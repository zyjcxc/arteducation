package com.edu.admin.education.convert;

import com.edu.admin.education.command.ArtNewsCommand;
import com.edu.admin.education.dto.ArtNewsDto;
import com.edu.admin.education.model.ArtNews;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtNewsCommand to ArtNews转换类
 * @author mengqa
 * @date 2019-11-05 14:59:36
 */
public class ArtNewsConverter {

    /**
     * Convert ArtNewsCommand to ArtNews
     * @param artNewsCommand
     * @return
     */
    public static ArtNews convertToArtNews(ArtNewsCommand artNewsCommand) {
        if (artNewsCommand == null) {
            return null;
        }
        ArtNews artNews = new ArtNews();

        artNews.setTitle(artNewsCommand.getTitle());
        artNews.setAuthor(artNewsCommand.getAuthor());
        artNews.setSource(artNewsCommand.getSource());
        artNews.setContent(artNewsCommand.getContent());
        artNews.setState(artNewsCommand.getState());
        artNews.setType(artNewsCommand.getType());

        return artNews;
    }

    /**
     * Convert ArtNewsCommand to ArtNews
     * @return
     */
    public static ArtNewsDto convertToArtNewsDto(ArtNews artNews) {
        if (artNews == null) {
            return null;
        }
        ArtNewsDto artNewsDto = new ArtNewsDto();

        artNewsDto.setTitle(artNews.getTitle());
        artNewsDto.setAuthor(artNews.getAuthor());
        artNewsDto.setSource(artNews.getSource());
        artNewsDto.setContent(artNews.getContent());
        artNewsDto.setState(artNews.getState());
        artNewsDto.setType(artNews.getType());
        artNewsDto.setCreateUserId(artNews.getCreateUserId());
        artNewsDto.setCreatetime(artNews.getCreatetime());
        artNewsDto.setUpdatetime(artNews.getUpdatetime());
        artNewsDto.setId(artNews.getId());

        artNewsDto.setTypeName(getTypeName(artNews.getType()));


        return artNewsDto;
    }

    private static String getTypeName(Integer type) {
        switch (type) {
            case 1:
                return "新闻";
            case 2:
                return "公告";
            default:
                break;
        }
        return null;
    }

    /**
     * Convert ArtNews to ArtNewsCommand
     * @param artNews
     * @return
     */
    public static ArtNewsCommand convertToArtNewsCommand(ArtNews artNews) {
        if (artNews == null) {
            return null;
        }
        ArtNewsCommand artNewsCommand = new ArtNewsCommand();


        return artNewsCommand;
    }

    /**
     * 批量Convert ArtNewsCommand convertToList ArtNews
     * @param artNewsCommandList
     * @return
     */
     public static List<ArtNews> convertToListArtNews(List<ArtNewsCommand> artNewsCommandList) {
        List<ArtNews> list = new ArrayList<>();
        artNewsCommandList.forEach(artNewsCommand -> list.add(convertToArtNews(artNewsCommand)));
        return list;
    }
    
    /**
     * 批量Convert  ArtNews convertToList ArtNewsCommand
     * @param artNewsList
     * @return
     */
     public static List<ArtNewsCommand> convertToListArtNewsCommand(List<ArtNews> artNewsList) {
        List<ArtNewsCommand> list = new ArrayList<>();
        artNewsList.forEach(artNews -> list.add(convertToArtNewsCommand(artNews)));
        return list;
    }

    /**
     * 批量Convert  ArtNews convertToList ArtNewsCommand
     * @param artNewsList
     * @return
     */
    public static List<ArtNewsDto> convertToListArtNewsDto(List<ArtNews> artNewsList) {
        List<ArtNewsDto> list = new ArrayList<>();
        artNewsList.forEach(artNews -> list.add(convertToArtNewsDto(artNews)));
        return list;
    }
}
