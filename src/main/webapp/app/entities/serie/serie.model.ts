import dayjs from 'dayjs/esm';

export interface ISerie {
  id?: number;
  nom?: string | null;
  dateHeureAjout?: dayjs.Dayjs | null;
}

export class Serie implements ISerie {
  constructor(public id?: number, public nom?: string | null, public dateHeureAjout?: dayjs.Dayjs | null) {}
}

export function getSerieIdentifier(serie: ISerie): number | undefined {
  return serie.id;
}
