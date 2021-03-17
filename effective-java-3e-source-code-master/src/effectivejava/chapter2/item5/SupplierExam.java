package effectivejava.chapter2.item5;

import java.util.function.Supplier;

public class SupplierExam {

    Mosaic create(Supplier<? extends Tile> tileFactory) {
        Tile tile = tileFactory.get();
        return new Mosaic(tile);
    }

    public static void main(String[] args) {
        SupplierExam exam = new SupplierExam();
        Mosaic 타일2모자이크 = exam.create(() -> new CustomTile("타일2"));
    }
}

class Mosaic {
    public Mosaic(Tile tile) {
        System.out.println("create mosaic by " + tile);
    }
}

class Tile {
    private final String name;

    public Tile() {
        this("기본타일");
    }

    public Tile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "name='" + name + '\'' +
                '}';
    }
}

class CustomTile extends Tile {
    public CustomTile(String name) {
        super(name);
    }
}