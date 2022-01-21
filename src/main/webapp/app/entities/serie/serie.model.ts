import dayjs from 'dayjs/esm';
import { ISaison } from 'app/entities/saison/saison.model';

export interface ISerie {
  id?: number;
  nom?: string | null;
  dateHeureAjout?: dayjs.Dayjs | null;
  saisons?: ISaison | null;
}

export class Serie implements ISerie {
  constructor(
    public id?: number,
    public nom?: string | null,
    public dateHeureAjout?: dayjs.Dayjs | null,
    public saisons?: ISaison | null
  ) {}
}

export function getSerieIdentifier(serie: ISerie): number | undefined {
  return serie.id;
}
