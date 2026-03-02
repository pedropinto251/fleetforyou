package com.fleetforyou.fleetforyou.Domain.DTO.Form;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record FormDTO(int id, int satisfaction, String observation, Rental rental) {
    public static FormDTOBuilder builder(){return new FormDTOBuilder();}

    public static class FormDTOBuilder{
        private int id;
        private int satisfaction;
        private String observation;
        private Rental rental;

        FormDTOBuilder() {
        }

        public FormDTOBuilder id(int id){
            this.id = id;
            return this;
        }

        public FormDTOBuilder satisfaction(int satisfaction){
            this.satisfaction = satisfaction;
            return this;
        }

        public FormDTOBuilder observation(String observation){
            this.observation = observation;
            return this;
        }

        public FormDTOBuilder rental(Rental rental){
            this.rental = rental;
            return this;
        }

        public FormDTO build() {
            return new FormDTO(this.id, this.satisfaction, this.observation, this.rental);
        }

        public String toString() {
            return "FormDTO.FormDTOBuilder(id=" + this.id + ", satisfaction=" + this.satisfaction + ", observation=" + this.observation + ", rental=" + this.rental + ")";
        }
    }
}
