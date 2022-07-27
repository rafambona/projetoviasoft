package com.api.projetoviasoft.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.api.projetoviasoft.models.StatusServicoModel;


@Component
public class ParserComponent {
	private Document document;	

	/*
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
			ParserComponent parser = new ParserComponent(doc);
			parser.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

	public List<StatusServicoModel> parse() throws IOException {
		document = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
		Elements elements = document.getElementsByClass("tabelaListagemDados");
		
		Elements trs = elements.select("tr");
		Elements ths = elements.select("th");
		
		List<StatusServicoModel> lista = new ArrayList<StatusServicoModel>();
				
		for (Element tr : trs) {
			String estado = tr.select("td").eq(0).text();			
			for (int i = 1; i < tr.select("td").size(); i++) {				
				String status = this.getStatus(tr.select("td").eq(i).select("img").attr("src"));
				String servico = ths.eq(i).text();
				var statusServicoModel = new StatusServicoModel(estado, status, servico);
				//statusServicoService.save(statusServicoModel);
				lista.add(statusServicoModel);
				
			}			
		}
		return lista;
	}	
	
	private String getStatus(String cor) {
		String ret = "";
		switch (cor) {
			case "imagens/bola_verde_P.png":
				ret = "Success";
				break;
			case "imagens/bola_vermelho_P.png":
				ret = "Error";
				break;
			case "imagens/bola_amarela_P.png":
				ret = "Warning";
				break;
			default:
				ret = "null";
				break;
		}
		return ret;
	}
}
