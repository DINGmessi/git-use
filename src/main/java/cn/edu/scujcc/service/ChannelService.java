package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**�ṩƵ�����ҵ���߼���
 * @author dell
 * 
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * ��ȡһ��Ƶ��
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
	 * ��ȡ����Ƶ��
	 * @return Ƶ��List
	 */
	public List<Channel> getAllChannels(){
		return repo.findAll();
	}
	
	/**
	 * ɾ��ָ��Ƶ��
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		return result;
	}
	
	/**
	 * ����һ��Ƶ��
	 * @param c �����µ�Ƶ��
	 * @return ���º��Ƶ��
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
	 * �½�Ƶ��
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