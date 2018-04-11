package hei.tresorock.DAO;

import hei.tresorock.entities.Participe;

import java.util.List;

public interface ParticipeDao {

        public List<Participe> listParticipe();

        Participe getParticipe(Integer idSoiree, Integer idClient);

        public Participe addParticipe (Participe participe);

        Double countRecetteTotale(Integer idSoiree);
}
