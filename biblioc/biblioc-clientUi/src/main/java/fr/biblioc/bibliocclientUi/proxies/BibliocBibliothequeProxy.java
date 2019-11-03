package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.AuteurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "biblioc-bibliotheque", url = "localhost:9002")
public interface BibliocBibliothequeProxy {

   @GetMapping(value = "/Auteurs")
    List<AuteurBean> listAuteurs();

   @GetMapping(value = "/Auteurs/{id}")
    AuteurBean getAuteur(@PathVariable("id") int id);
}
