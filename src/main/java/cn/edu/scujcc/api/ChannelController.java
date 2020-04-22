package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Channel;
import cn.edu.scujcc.service.ChannelService;

/**
 * 频道接口，提供客户端访问的入口
 * @author dell
 *
 */

@RestController
@RequestMapping("/channel")
public class ChannelController {
	public static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService service;
	
	/**
	 * 获取所有频道
	 * @return 所有频道的JSON数组
	 */
	@GetMapping
	public List<Channel> getAllChannels() {
		logger.info("正在查找所有频道信息!!!");
		List<Channel> results = service.getAllChannels();
		logger.debug("所有频道的数量是：" + results.size());
		return results;
	}
	
	/**
	 * 获取一个指定频道的JSON数据
	 * @param id 指定频道的编号
	 * @return id对应频道的JSON数据
	 */
	@GetMapping("/{id}")
	public Channel getChannel(@PathVariable String id) {
		logger.info("正在....." + id);
		Channel c = service.getChannel(id);
		if (c != null) {
			return c;
		} else {
			logger.error("找不到指定的频道。");
			return null;
		}
	}
	
	/**
	 * 删除一个指定的频道
	 * @param id 待删除频道的编号
	 * @return 成功或失败的消息
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable String id) {
		System.out.println("即将删除频道，id=" + id);
		boolean result = service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("Deleted successfully");
		} else {
			return ResponseEntity.ok().body("Deleted failed");
		}
	}
	
	/**
	 * 新建一个频道
	 * @param 待新建频道的数据
	 * @return 保存后的频道数据
	 */
	@PostMapping
	public Channel createChannel(@RequestBody Channel c) {
		System.out.println("即将新建频道，频道数据：" + c);
		Channel saved = service.createChannel(c);
		return saved;
	}
	
	/**
	 * 更新一个频道
	 * @param 待更新频道的数据
	 * @return 更新后的频道数据
	 */
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		System.out.println("即将更新频道，频道数据：" + c);
		Channel updated = service.updateChannel(c);
		return updated;
	}
	@GetMapping("/t/{title}")
	public List<Channel> searchByTitle(@PathVariable String title){
		return service.searcha(title);
	}
	
	@GetMapping("/q/{quality}")
	public List<Channel> searchByQuality(@PathVariable String quality){
		return service.searchb(quality);
	}
}
