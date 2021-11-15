package gilko.marcin.Auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void save(AuctionParticipant auctionParticipant) {
		repo.save(auctionParticipant);
	}
	
	public AuctionParticipant get(AuctionParticipantId auctionParticipantId) {
		
		return repo.findByPrimaryKey(auctionParticipantId);
	}
	
	public void delete(AuctionParticipant auctionParticipant) {
		repo.delete(auctionParticipant);
	}
}
