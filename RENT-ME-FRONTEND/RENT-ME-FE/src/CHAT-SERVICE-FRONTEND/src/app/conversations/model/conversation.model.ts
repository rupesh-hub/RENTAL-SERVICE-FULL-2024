import {BaseUser} from "../../shared/model/user.model";
import {Dayjs} from "dayjs";

export interface Conversation {
  publicId: string,
  name: string,
  members: Array<BaseUser>,
  messages: Array<Message>,
  active?: boolean
}

export interface ConversationToCreate {
  members: Array<String>,
  name: string
}

export type SendState = "TO_SEND" | "SENT" | "RECEIVED" | "READ";
export type MessageType = "TEXT" | "AUDIO" | "VIDEO" | "PICTURE";

export interface Message {
  textContent: string,
  sendDate?: Dayjs,
  conversationId: string,
  state?: SendState,
  publicId: string,
  type: MessageType,
  mediaContent?: File,
  mimeType?: string,
  senderId: string

}

export interface MessageMarkAsViewedResponse {
  conversationPublicId: string,
  nbMessagesUpdated: number
}
