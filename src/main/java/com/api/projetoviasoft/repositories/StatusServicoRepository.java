package com.api.projetoviasoft.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.projetoviasoft.models.StatusServicoModel;

@Repository
public interface StatusServicoRepository extends JpaRepository<StatusServicoModel, UUID>{
	
	@Query(
		value = "SELECT DISTINCT estado,\r\n"
				+ "       servico,\r\n"
				+ "       status_cor,\r\n"
				+ "       MAX(created)\r\n"
				+ "FROM tb_status_servico\r\n"
				+ "GROUP BY estado, servico, status_cor\r\n"
				+ "ORDER BY max(created) DESC, estado",
		nativeQuery = true
	)
	List<Object> getAll();

	List<StatusServicoModel> findByEstado(String estado);
	
	@Query(
		value = "SELECT id,\r\n"
				+ "     estado,\r\n"
				+ "     servico,\r\n"
				+ "     status_cor,\r\n"
				+ "     created\r\n"
				+ "FROM tb_status_servico\r\n"
				+ "WHERE CAST(created AS Date) = :dateParam\r\n"
				+ "ORDER BY estado",
		nativeQuery = true
	)
	List<StatusServicoModel> findAllWithCreationTime(@Param("dateParam") Date dateParam);
	
	@Query(
		value = "SELECT estado\r\n"
				+ "FROM tb_status_servico\r\n"
				+ "WHERE status_cor = 'Error'\r\n"
				+ "GROUP BY estado\r\n"
				+ "HAVING COUNT(*) = (\r\n"
				+ "	SELECT MAX(contador) FROM (\r\n"
				+ "		SELECT COUNT(status_cor) AS contador \r\n"
				+ "		FROM tb_status_servico \r\n"
				+ "		WHERE status_cor = 'Error' \r\n"
				+ "		GROUP BY estado) \r\n"
				+ "	AS maximo\r\n"
				+ ")",
		nativeQuery = true
	)
	List<Object> findMaxError();
}
