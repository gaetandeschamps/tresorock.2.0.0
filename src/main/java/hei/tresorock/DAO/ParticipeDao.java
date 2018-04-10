package hei.tresorock.DAO;

import hei.tresorock.entities.Participe;

import java.util.List;

public interface ParticipeDao {

        public List<Participe> listParticipe();

        public Participe getParticipe(Integer idParticipe);

        public Participe addParticipe (Participe participe);

        Double countRecetteTotale(Integer idSoiree);
}
