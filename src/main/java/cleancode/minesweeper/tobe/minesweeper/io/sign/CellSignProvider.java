package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;

import java.util.Arrays;

public enum CellSignProvider implements CellSignProvidable {

    EMPTY(CellSnapshotStatus.EMPTY){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return EMPTY_SIGN;
        }
    },
    FLAG(CellSnapshotStatus.FLAG){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return FLAG_SIGN;
        }
    },
    LANDMINE(CellSnapshotStatus.LANDMINE){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return LAND_MINE_SIGN;
        }
    },
    NUMBER(CellSnapshotStatus.NUMBER){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return String.valueOf(cellSnapshot.getNearbyLandMineCount());
        }
    },
    UNCHECKED(CellSnapshotStatus.UNCHECKED){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return UNCHECKED_SIGN;
        }
    },
    ;

    private final CellSnapshotStatus cellSnapshotStatus;

    private static final String EMPTY_SIGN = "■";
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";

    CellSignProvider(CellSnapshotStatus cellSnapshotStatus) {
        this.cellSnapshotStatus = cellSnapshotStatus;
    }

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(cellSnapshotStatus);
    }

    public static String findCellSignFrom(CellSnapshot cellSnapshot) {
        CellSignProvider cellSignProvider = findBy(cellSnapshot);
        return cellSignProvider.provide(cellSnapshot);
    }

    private static CellSignProvider findBy(CellSnapshot cellSnapshot) {
        return Arrays.stream(values())
                .filter(provider -> provider.supports(cellSnapshot))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
    }
}
