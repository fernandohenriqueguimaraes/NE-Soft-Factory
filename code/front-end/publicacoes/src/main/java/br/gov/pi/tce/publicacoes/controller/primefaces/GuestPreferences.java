/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.gov.pi.tce.publicacoes.controller.primefaces;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GuestPreferences implements Serializable {
        
	private static final long serialVersionUID = 1L;

	private String theme = "blue";
    
    private String layout = "horizon";
            
    private boolean overlayMenu = false;
    
    private boolean slimMenu = false;
    
    private boolean darkMenu = false;
                
	public String getTheme() {		
		return theme;
	}
    
    public String getLayout() {		
        return layout;
    }
    
	public void setTheme(String theme) {
		this.theme = theme;
	}
    
    public void setLayout(String layout) {
		this.layout = layout;
	}
        
    public boolean isOverlayMenu() {
        return this.overlayMenu;
    }
    
    public void setOverlayMenu(boolean value) {
        this.overlayMenu = value;
        this.slimMenu = false;
    }
    
    public boolean isSlimMenu() {
        return this.slimMenu;
    }
    
    public void setSlimMenu(boolean value) {
        this.slimMenu = value;
    }
    
    public boolean isDarkMenu() {
        return this.darkMenu;
    }
    
    public void setDarkMenu(boolean value) {
        this.darkMenu = value;
    }
}
