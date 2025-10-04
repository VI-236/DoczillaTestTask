package org.VI__236.models.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Setter
    @Getter
    private Color color;

    @Setter
    @Getter
    private PlayerType playerType;
}
