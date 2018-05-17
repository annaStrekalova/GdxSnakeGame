/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeGame.model.events;

import java.util.EventListener;
import java.util.EventObject;

/**
 *
 * @author Анна
 */
public interface FieldListener extends EventListener{
    public void collision(EventObject e);
}
