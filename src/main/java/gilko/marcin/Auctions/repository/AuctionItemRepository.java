package gilko.marcin.Auctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.Auctions.auction.AuctionItem;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long>{

}
