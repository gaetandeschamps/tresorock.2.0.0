package hei.tresorock.DAO;

import hei.tresorock.entities.Soiree;

import java.util.List;

public interface SoireeDao {

        public List<Soiree> listSoiree();

        public Soiree getSoiree(Integer idSoiree);

        public Soiree addSoiree(Soiree soiree);

        public int getSoireeEnCoursId();
}
