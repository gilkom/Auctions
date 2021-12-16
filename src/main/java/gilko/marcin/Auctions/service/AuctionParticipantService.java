package gilko.marcin.Auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gilko.marcin.Auctions.model.auction.AuctionItem;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipant;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipantId;
import gilko.marcin.Auctions.repository.AuctionParticipantRepository;


@Service
@Transactional
public class AuctionParticipantService {
	@Autowired
	private AuctionParticipantRepository repo;

	public List<AuctionParticipant> list(){
		return repo.findAll();
	}
	
	public void registerObserver(AuctionParticipant auctionParticipant) {
		repo.save(auctionParticipant);
	}
	
	public AuctionParticipant get(AuctionParticipantId auctionParticipantId) {
		
		return repo.findByPrimaryKey(auctionParticipantId);
	}
	
	public void delete(AuctionParticipant auctionParticipant) {
		repo.delete(auctionParticipant);
	}
	
	public void unregisterObserver(AuctionParticipant auctionParticipant) {
		auctionParticipant.setActive(false);
		repo.save(auctionParticipant);
	}
	
	public List<AuctionParticipant> listId(long auction_id){
		return repo.findByAuction_id(auction_id);
	}
	public List<Long> listByParticipantId(long participant_id){
		return repo.findByParticipant_id(participant_id);
	}
}
