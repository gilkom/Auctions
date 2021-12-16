package gilko.marcin.Auctions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gilko.marcin.Auctions.model.auction.AuctionItem;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipant;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipantId;

public interface AuctionParticipantRepository extends JpaRepository<AuctionParticipant, AuctionParticipantId>{
	AuctionParticipant findByPrimaryKey(AuctionParticipantId auctionParticipantId);
	
	@Query("SELECT k FROM AuctionParticipant k WHERE k.primaryKey.auctionItem.auction_item_id = :auction_id")
	public List<AuctionParticipant> findByAuction_id(long auction_id);

	@Query("SELECT k FROM AuctionParticipant k WHERE k.primaryKey.participant.participant_id = :participant_id")
	public List<Long> findByParticipant_id(long participant_id);
}
