package br.com.veiculo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.veiculo.model.Veiculo;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private List<Veiculo> veiculos = new ArrayList<>();

    @GetMapping
    public List<Veiculo> listar() {
        return veiculos;
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable Long id) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Veiculo adicionar(@RequestBody Veiculo veiculo) {
        veiculos.add(veiculo);
        return veiculo;
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        Veiculo veiculo = buscarPorId(id);
        if (veiculo != null) {
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
        }
        return veiculo;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        veiculos.removeIf(veiculo -> veiculo.getId().equals(id));
    }
}
