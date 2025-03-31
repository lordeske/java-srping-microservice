package com.porudzbina;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record   StavkePorudzbineReq (



       Integer idPorudzbine,

       Integer proizvodId,

       double kolicina

){




}
