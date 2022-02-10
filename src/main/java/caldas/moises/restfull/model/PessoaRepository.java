package caldas.moises.restfull.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class PessoaRepository {
	private Map<Integer, Pessoa> pessoas = new HashMap<>();
	private Integer count = 0;
	
	public void add(Pessoa pessoa) {
		pessoa.setId(++count);
		pessoas.put(count, pessoa);
	}
	
	public void remove(Integer id) {
		pessoas.remove(id);
	}
	
	public Pessoa load(Integer id) {
		return pessoas.get(id);
	}
	
	public void update(Pessoa pessoa) {
		pessoas.put(pessoa.getId(), pessoa);
	}
	
	public List<Pessoa> list(){
		return new ArrayList<>(pessoas.values());
	}
}
