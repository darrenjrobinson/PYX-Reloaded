package net.socialgamer.cah.handlers;

import io.undertow.server.HttpServerExchange;
import net.socialgamer.cah.Constants.AjaxOperation;
import net.socialgamer.cah.Constants.AjaxRequest;
import net.socialgamer.cah.Constants.ErrorCode;
import net.socialgamer.cah.JsonWrapper;
import net.socialgamer.cah.data.Game;
import net.socialgamer.cah.data.GameManager;
import net.socialgamer.cah.data.User;
import net.socialgamer.cah.servlets.Annotations;
import net.socialgamer.cah.servlets.BaseCahHandler;
import net.socialgamer.cah.servlets.Parameters;

public class JudgeSelectHandler extends GameWithPlayerHandler {
    public static final String OP = AjaxOperation.JUDGE_SELECT.toString();

    public JudgeSelectHandler(@Annotations.GameManager GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public JsonWrapper handleWithUserInGame(User user, Game game, Parameters params, HttpServerExchange exchange) throws BaseCahHandler.CahException {
        String cardIdStr = params.get(AjaxRequest.CARD_ID);
        if (cardIdStr == null || cardIdStr.isEmpty())
            throw new BaseCahHandler.CahException(ErrorCode.NO_CARD_SPECIFIED);

        int cardId;
        try {
            cardId = Integer.parseInt(cardIdStr);
        } catch (NumberFormatException ex) {
            throw new BaseCahHandler.CahException(ErrorCode.INVALID_CARD, ex);
        }

        game.judgeCard(user, cardId);
        return JsonWrapper.EMPTY;
    }
}
