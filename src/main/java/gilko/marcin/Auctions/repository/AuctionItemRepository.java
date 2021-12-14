package gilko.marcin.Auctions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gilko.marcin.Auctions.model.auction.AuctionItem;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long>{
	@Query("SELECT a FROM AuctionItem a order by auction_item_id")
	public List<AuctionItem> findAllByOrderByAuction_item_idDesc();
	
}
