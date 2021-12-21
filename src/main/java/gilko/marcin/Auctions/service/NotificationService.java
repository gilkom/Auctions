package gilko.marcin.Auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gilko.marcin.Auctions.model.notification.Notification;
import gilko.marcin.Auctions.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {
	@Autowired
	private NotificationRepository repo;
	
	public List<Notification> listAll(){
		return repo.findAll();
	}
	public void save(Notification notification) {
		repo.save(notification);
	}
	
	public Notification get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public void saveList(List<Notification> notificationList) {
		repo.saveAll(notificationList);
	}
	
	public List<Notification> listByParticipantId(Long participant_id){
		return repo.findByParticipant_id(participant_id);
	}
	
	public List<Notification> auctionHistory(Long auction_id, Long owner){
		return repo.findbyAuctionIdAndOwner(auction_id, owner);
	}
	

}
