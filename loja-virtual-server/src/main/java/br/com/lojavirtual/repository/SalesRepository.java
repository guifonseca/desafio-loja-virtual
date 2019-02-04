package br.com.lojavirtual.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojavirtual.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

	@Query("SELECT s FROM SALES s JOIN s.product p WHERE p.id = :id")
	public Set<Sales> findAllSalesByProductId(@Param("id") Long id);
	
	@Query("SELECT s FROM SALES s JOIN s.product p WHERE s.idTransaction = :idTransaction")
	public Sales findSaleByIdTransaction(@Param("idTransaction") Long idTransaction);

}
