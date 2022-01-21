import { ISerie } from 'app/entities/serie/serie.model';
import { IEpisode } from 'app/entities/episode/episode.model';

export interface ISaison {
  id?: number;
  nom?: string | null;
  series?: ISerie[] | null;
  episodes?: IEpisode | null;
}

export class Saison implements ISaison {
  constructor(public id?: number, public nom?: string | null, public series?: ISerie[] | null, public episodes?: IEpisode | null) {}
}

export function getSaisonIdentifier(saison: ISaison): number | undefined {
  return saison.id;
}
