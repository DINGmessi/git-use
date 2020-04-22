package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**提供频道相关业务逻辑。
 * @author dell
 * 
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * 获取一个频道
	 * @param id
	 * @return
	 */
	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	/**
	 * 获取所有频道
	 * @return 频道List
	 */
	public List<Channel> getAllChannels(){
		return repo.findAll();
	}
	
	/**
	 * 删除指定频道
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		return result;
	}
	
	/**
	 * 更新一个频道
	 * @param c 待更新的频道
	 * @return 更新后的频道
	 */
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if(c.getTitle() != null) {
			saved.setTitle(c.getTitle());
		}
		if(c.getQuality() != null) {
			saved.setQuality(c.getQuality());
		}
		if(c.getUrl() != null) {
			saved.setUrl(c.getUrl());
		}
		
		return repo.save(saved);
	}
	
	/**
	 * 新建频道
	 * @param c
	 * @return
	 */
	public Channel createChannel(Channel c) {
		return repo.save(c);
	}

	public List<Channel> searcha(String title) {
		return repo.findByTitle(title);
	}

	public List<Channel> searchb(String quality) {
		return repo.findByQuality(quality);
	}
}
